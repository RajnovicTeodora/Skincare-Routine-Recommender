package sbnz.skincare.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javassist.NotFoundException;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sbnz.skincare.dto.ProductReactionDTO;
import sbnz.skincare.dto.RequestRoutineDTO;
import sbnz.skincare.dto.RoutineWithReactionDTO;
import sbnz.skincare.facts.*;
import sbnz.skincare.facts.drools.RecommendationInput;
import sbnz.skincare.facts.enumerations.*;
import sbnz.skincare.repository.PatientRepository;
import sbnz.skincare.repository.RoutineRepository;

import javax.validation.constraints.NotNull;

@Service
public class SkincareRoutineService {

    private static Logger log = LoggerFactory.getLogger(SkincareRoutineService.class);

    private final KieContainer kieContainer;

    private final SkinTypeCharacteristicsService skinTypeCharacteristicsService;

    private final RoutineRepository routineRepository;

    private final PatientRepository patientRepository;

    private final ProductReactionService productReactionService;

    @Autowired
    public SkincareRoutineService(KieContainer kieContainer,
                                  SkinTypeCharacteristicsService skinTypeCharacteristicsService,
                                  RoutineRepository routineRepository,
                                  PatientRepository patientRepository,
                                  ProductReactionService productReactionService) {
        this.kieContainer = kieContainer;
        this.skinTypeCharacteristicsService = skinTypeCharacteristicsService;
        this.routineRepository = routineRepository;
        this.patientRepository = patientRepository;
        this.productReactionService = productReactionService;
    }

    public Routine getRoutineRecommendation(@NotNull RequestRoutineDTO request) throws NotFoundException {

        KieSession kSession = kieContainer.newKieSession();

        Patient patient = patientRepository.findByUsername(request.getPatientUsername())
                .orElseThrow(
                        () -> new NotFoundException(
                                String.format("Patient with username %s not found", request.getPatientUsername())
                        )
                );

        RecommendationInput recommendationInput = new RecommendationInput(request, patient.getBirthday());

        kSession.insert(this.skinTypeCharacteristicsService.getAll());

        List<SkinCharacteristic> scp = new ArrayList<SkinCharacteristic>();
        scp.add(SkinCharacteristic.BLEMISHES);
        scp.add(SkinCharacteristic.VISIBLE_PORES);
        scp.add(SkinCharacteristic.OILY_SKIN);

        Goal g1 = Goal.REDUCE_ACNE;
        Goal g2 = Goal.REDUCE_OILINESS;
        Goal g3 = Goal.REDUCE_REDNESS;

        List<Goal> gl1 = new ArrayList<Goal>();
        gl1.add(g1);
        gl1.add(g2);

        List<Goal> gl2 = new ArrayList<Goal>();
        gl2.add(g1);
        gl2.add(g3);

        List<Goal> gl3 = new ArrayList<Goal>();
        gl3.add(g3);

        Ingredient i1 = new Ingredient("i1", AcneType.PUSTULES, AgeGroup.YOUTH, SkinType.OILY, gl1);
        Ingredient i2 = new Ingredient("i2", AcneType.PAPULES, AgeGroup.YOUTH, SkinType.OILY, gl3);

        List<String> alergies1 = new ArrayList<String>();
        alergies1.add("i2");

        List<Ingredient> il1 = new ArrayList<>();
        il1.add(i1);

        List<Ingredient> il2 = new ArrayList<>();
        il2.add(i2);

        List<Ingredient> il3 = new ArrayList<>();
        il3.add(i1);

        Product p1 = new Product("p1", ProductType.SERUM, "m1", "ui1", "image", 10, il1);
        Product p3 = new Product("p3", ProductType.SERUM, "m2","ui2",  "image", 10, il1);
        Product p2 = new Product("p2", ProductType.EXFOLIATOR, "m2","ui1",  "image", 20, il2);

        RecommendationInput sti = new RecommendationInput(scp, null, LocalDate.now().minusYears(17), null, gl2, AcneType.PUSTULES, alergies1, "m2");

        kSession.insert(i1);
        kSession.insert(i2);
        kSession.insert(p1);
        kSession.insert(p2);
        kSession.insert(p3);
        kSession.insert(sti);

        kSession.fireAllRules();

        System.out.println(sti.getDiagnosedSkinType());
        System.out.println(sti.getAgeGroup());
        kSession.dispose();
        return new Routine();
    }

    public List<Routine> findByPatientUsername(String username){
        return this.routineRepository.findByPatientUsername(username);
    }

    public List<RoutineWithReactionDTO> findPatientRoutinesWithReaction(String username){
        List<Routine> routines = findByPatientUsername(username);
        List<RoutineWithReactionDTO> routinesWithReaction = new ArrayList<>();
        routines.forEach(routine -> {
            List<ProductReactionDTO> productReactions = new ArrayList<>();
            routine.getProducts().forEach(product -> {
                ProductReaction productReaction = this.productReactionService
                        .findByProductAndPatient(product.getId(), username);
                if(productReaction == null) productReactions.add(new ProductReactionDTO(product));
                else productReactions.add(new ProductReactionDTO(productReaction));
            });
            routinesWithReaction.add(new RoutineWithReactionDTO(routine.getStartDate(), productReactions));
        });
        return routinesWithReaction;
    }
}
