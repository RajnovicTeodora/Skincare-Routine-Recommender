package sbnz.skincare.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sbnz.skincare.dto.RequestRoutineDTO;
import sbnz.skincare.facts.Goal;
import sbnz.skincare.facts.Ingredient;
import sbnz.skincare.facts.Product;
import sbnz.skincare.facts.Routine;
import sbnz.skincare.facts.SkinTypeCharacteristics;
import sbnz.skincare.facts.drools.RecommendationInput;
import sbnz.skincare.facts.enumerations.AcneType;
import sbnz.skincare.facts.enumerations.AgeGroup;
import sbnz.skincare.facts.enumerations.ProductType;
import sbnz.skincare.facts.enumerations.SkinCharacteristic;
import sbnz.skincare.facts.enumerations.SkinType;
import sbnz.skincare.service.SkinTypeCharacteristicsService;

@Service
public class SkincareRoutineService {

	private static Logger log = LoggerFactory.getLogger(SkincareRoutineService.class);

	private final KieContainer kieContainer;

	private SkinTypeCharacteristicsService skinTypeCharacteristicsService;

	@Autowired
	public SkincareRoutineService(KieContainer kieContainer,
			SkinTypeCharacteristicsService skinTypeCharacteristicsService) {
		this.kieContainer = kieContainer;
		this.skinTypeCharacteristicsService = skinTypeCharacteristicsService;
	}

	public Routine getRoutineRecommendation(RequestRoutineDTO request) {

			KieSession kSession = kieContainer.newKieSession();

			SkinTypeCharacteristics stc1 = new SkinTypeCharacteristics();
			List<SkinCharacteristic> sc1 = new ArrayList<SkinCharacteristic>();
			stc1.setSkinType(SkinType.COMBINED);
			sc1.add(SkinCharacteristic.OILY_SKIN);
			sc1.add(SkinCharacteristic.VISIBLE_PORES);
			sc1.add(SkinCharacteristic.DRY_SKIN);
			sc1.add(SkinCharacteristic.BLEMISHES);
			
			stc1.setSkinCharacteristics(sc1);

			SkinTypeCharacteristics stc2 = new SkinTypeCharacteristics();
			List<SkinCharacteristic> sc2 = new ArrayList<SkinCharacteristic>();
			stc2.setSkinType(SkinType.OILY);
			sc2.add(SkinCharacteristic.BLEMISHES);
			sc2.add(SkinCharacteristic.VISIBLE_PORES);
			sc2.add(SkinCharacteristic.OILY_SKIN);

			stc2.setSkinCharacteristics(sc2);
			
			SkinTypeCharacteristics stc3 = new SkinTypeCharacteristics();
			List<SkinCharacteristic> sc3 = new ArrayList<SkinCharacteristic>();
			stc3.setSkinType(SkinType.NORMAL);
			sc3.add(SkinCharacteristic.RADIANT_COMPLEXION);

			stc3.setSkinCharacteristics(sc3);
			
			SkinTypeCharacteristics stc4 = new SkinTypeCharacteristics();
			List<SkinCharacteristic> sc4 = new ArrayList<SkinCharacteristic>();
			stc4.setSkinType(SkinType.DRY);
			sc4.add(SkinCharacteristic.TIGHT_SKIN);
			sc4.add(SkinCharacteristic.DRY_SKIN);
			sc4.add(SkinCharacteristic.ITCHY_SKIN);
			sc4.add(SkinCharacteristic.RED_PATCHES);

			stc4.setSkinCharacteristics(sc4);
			
			SkinTypeCharacteristics stc5 = new SkinTypeCharacteristics();
			List<SkinCharacteristic> sc5 = new ArrayList<SkinCharacteristic>();
			stc5.setSkinType(SkinType.SENSITIVE);
			sc5.add(SkinCharacteristic.DRY_SKIN);
			sc5.add(SkinCharacteristic.BLEMISHES);
			sc5.add(SkinCharacteristic.ITCHY_SKIN);
			sc5.add(SkinCharacteristic.RED_PATCHES);

			stc5.setSkinCharacteristics(sc5);
			
			List<SkinCharacteristic> scp = new ArrayList<SkinCharacteristic>();
			scp.add(SkinCharacteristic.BLEMISHES);
			scp.add(SkinCharacteristic.VISIBLE_PORES);
			scp.add(SkinCharacteristic.OILY_SKIN);
			
			Goal g1 = new Goal("g1");
			Goal g2 = new Goal("g2");
			Goal g3 = new Goal("g3");
			Goal g4 = new Goal("g4");
			
			List<Goal> gl1 = new ArrayList<Goal>();
			gl1.add(g1);
			gl1.add(g2);
			
			List<String> gl2 = new ArrayList<String>();
			gl2.add(g1.getName());
			gl2.add(g3.getName());
			
			List<Goal> gl3 = new ArrayList<Goal>();
			gl3.add(g4);
			
			Ingredient i1 = new Ingredient("i1", AcneType.PUSTULES, AgeGroup.YOUTH, SkinType.OILY, gl1 );
			Ingredient i2 = new Ingredient("i2", AcneType.PAPULES, AgeGroup.YOUTH, SkinType.OILY, gl3 );
			
			List<String> alergies1 = new ArrayList<String>();
			alergies1.add("i2");
			
			List<Ingredient> il1 = new ArrayList<>();
			il1.add(i1);
			
			List<Ingredient> il2 = new ArrayList<>();
			il2.add(i2);
			
			List<Ingredient> il3 = new ArrayList<>();
			il3.add(i1);
			
			Product p1 = new Product("p1", ProductType.SERUM, "m1", "image", 10, il1);
			Product p3 = new Product("p3", ProductType.SERUM, "m2", "image", 10, il1);
			Product p2 = new Product("p2", ProductType.EXFOLIATOR, "m2", "image", 20, il2);
					
			RecommendationInput sti = new RecommendationInput(null, scp, null, LocalDate.now().minusYears(17), null, gl2, AcneType.PUSTULES, alergies1, "m2");
			
			kSession.insert(stc1);
			kSession.insert(stc2);
        	kSession.insert(stc3);
        	kSession.insert(stc4);
        	kSession.insert(stc5);
        	kSession.insert(i1);
        	kSession.insert(i2);
        	kSession.insert(p1);
        	kSession.insert(p2);
        	kSession.insert(p3);
        	kSession.insert(sti);
        	
        	kSession.fireAllRules();
			
        	System.out.println(sti.getDiagnosedSkinType());
        	System.out.println(sti.getAgeGroup());
        	return new Routine();
	}
}
