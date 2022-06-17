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
import sbnz.skincare.repository.IngredientRepository;
import sbnz.skincare.repository.PatientRepository;
import sbnz.skincare.repository.ProductRepository;
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

	private final IngredientRepository ingredientRepository; // TODO move to service

	private final ProductRepository productRepository;

	@Autowired
	public SkincareRoutineService(KieContainer kieContainer,
			SkinTypeCharacteristicsService skinTypeCharacteristicsService, RoutineRepository routineRepository,
			PatientRepository patientRepository, ProductReactionService productReactionService,
			IngredientRepository ingredientRepository, ProductRepository productRepository) {
		this.kieContainer = kieContainer;
		this.skinTypeCharacteristicsService = skinTypeCharacteristicsService;
		this.routineRepository = routineRepository;
		this.patientRepository = patientRepository;
		this.productReactionService = productReactionService;
		this.ingredientRepository = ingredientRepository;
		this.productRepository = productRepository;
	}

	public Routine getRoutineRecommendation(@NotNull RequestRoutineDTO request)
			throws NotFoundException {

		KieSession kSession = kieContainer.newKieSession("recommendation_rules");

		// Find patient
		Patient patient = patientRepository.findByUsername(request.getPatientUsername())
				.orElseThrow(() -> new NotFoundException(
						String.format("Patient with username %s not found", request.getPatientUsername())));

		// Insert characteristics for each skin type
		for (SkinTypeCharacteristics stc : this.skinTypeCharacteristicsService.getAll()) {
			kSession.insert(stc);
		}

		// Insert ingredients
		for (Ingredient i : this.ingredientRepository.findAll()) {
			kSession.insert(i);
		}

		// Insert products
		for (Product p : this.productRepository.findAll()) {
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
		this.patientRepository.save(patient);

		return routine;
	}

	public List<Routine> findByPatientUsername(String username) {
		return this.routineRepository.findByPatientUsername(username);
	}

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
