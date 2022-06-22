package sbnz.skincare.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sbnz.skincare.dto.ProductReactionDTO;
import sbnz.skincare.dto.RequestRoutineDTO;
import sbnz.skincare.dto.RoutineWithReactionDTO;
import sbnz.skincare.facts.*;
import sbnz.skincare.facts.drools.RecommendationInput;
import sbnz.skincare.repository.RoutineRepository;

import javax.validation.constraints.NotNull;

@Service
public class SkincareRoutineService {

    private final KieContainer kieContainer;

    private final SkinTypeCharacteristicsService skinTypeCharacteristicsService;

    private final RoutineRepository routineRepository;

    private final PatientService patientService;

    private final ProductReactionService productReactionService;

    private final IngredientService ingredientService;

    private final ProductService productService;

    @Autowired
    public SkincareRoutineService(KieContainer kieContainer,
                                  SkinTypeCharacteristicsService skinTypeCharacteristicsService, RoutineRepository routineRepository,
                                  PatientService patientService, ProductReactionService productReactionService,
                                  IngredientService ingredientService, ProductService productService) {
        this.kieContainer = kieContainer;
        this.skinTypeCharacteristicsService = skinTypeCharacteristicsService;
        this.routineRepository = routineRepository;
        this.patientService = patientService;
        this.productReactionService = productReactionService;
        this.ingredientService = ingredientService;
        this.productService = productService;
    }

    public Routine getRoutineRecommendation(@NotNull RequestRoutineDTO request) {

        KieSession kSession = kieContainer.newKieSession("recommendation_rules");

        // Find patient
        Patient patient = patientService.findByUsername(request.getPatientUsername());

        // Insert characteristics for each skin type
        for (SkinTypeCharacteristics stc : this.skinTypeCharacteristicsService.getAll()) {
            kSession.insert(stc);
        }

        // Insert ingredients
        for (Ingredient i : this.ingredientService.getAll()) {
            kSession.insert(i);
        }

        // Insert products
        for (Product p : this.productService.getAll()) {
            kSession.insert(p);
        }

        // Create recommendation input
        RecommendationInput recommendationInput = new RecommendationInput(request, patient.getBirthday(),
                patient.getProductReactions());
        kSession.insert(recommendationInput);

        // Create routine
        Routine routine = new Routine(LocalDate.now(), new ArrayList<>(), patient);
        kSession.insert(routine);

        kSession.fireAllRules();
        kSession.dispose();

        // Save to database
        this.routineRepository.save(routine);
        this.patientService.save(patient);

        return routine;
    }

    public List<Routine> findByPatientUsername(String username) {
        return this.routineRepository.findByPatientUsername(username);
    }

    // Get patient routines with products with reactions
    public List<RoutineWithReactionDTO> findPatientRoutinesWithReaction(String username) {
        List<Routine> routines = findByPatientUsername(username);
        List<RoutineWithReactionDTO> routinesWithReaction = new ArrayList<>();
        routines.forEach(routine -> {
            List<ProductReactionDTO> productReactions = new ArrayList<>();
            routine.getProducts().forEach(product -> {
                ProductReaction productReaction = this.productReactionService.findByProductAndPatient(product.getId(),
                        username);
                if (productReaction == null)
                    productReactions.add(new ProductReactionDTO(product));
                else
                    productReactions.add(new ProductReactionDTO(productReaction));
            });
            routinesWithReaction.add(new RoutineWithReactionDTO(routine.getStartDate(), productReactions));
        });
        return routinesWithReaction;
    }
}
