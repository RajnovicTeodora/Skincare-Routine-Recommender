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

import sbnz.skincare.facts.Patient;
import sbnz.skincare.facts.Product;
import sbnz.skincare.facts.ProductReaction;
import sbnz.skincare.facts.Routine;
import sbnz.skincare.facts.enumerations.ProductType;

@Service
public class ReportService {

	private static Logger log = LoggerFactory.getLogger(ReportService.class);

	private final KieContainer kieContainer;

	@Autowired
	public ReportService(KieContainer kieContainer) {
		this.kieContainer = kieContainer;
	}

	public void getFrequentRoutineChangeReport() {
		KieSession kSession = kieContainer.newKieSession();

		Patient u1 = new Patient();
		Patient u2 = new Patient();

		Product p1 = new Product("p1", ProductType.EXFOLIATOR, "m1", "ui", "image", 10, null);
		Product p2 = new Product("p2", ProductType.EXFOLIATOR, "m2", "ui", "image", 20, null);
		Product p3 = new Product("p3", ProductType.EXFOLIATOR, "m1", "ui", "image", 10, null);
		
		p1.setId(1);
		p2.setId(2);
		p3.setId(3);
		
		List<Product> lp1 = new ArrayList<>();
		List<Product> lp2 = new ArrayList<>();
		List<Product> lp3 = new ArrayList<>();
		
		lp1.add(p1);
		lp2.add(p2);
		lp2.add(p3);
		lp3.add(p3);
		
		Routine r1 = new Routine(LocalDate.now(), lp1, u1);
		Routine r2 = new Routine(LocalDate.now().minusDays(5), lp2, u1);
		Routine r3 = new Routine(LocalDate.now().minusMonths(5), lp3, u1);

		List<Routine> rl1 = new ArrayList<>();
		rl1.add(r1);
		rl1.add(r2);
		rl1.add(r3);
		
		u1.setRoutines(rl1);
		u1.setProductReactions(null);
		
		//ProductReaction pr1 = new ProductReaction("f", p1, u1);
		//ProductReaction pr2 = new ProductReaction("ff", p2, u1);
		ProductReaction pr3 = new ProductReaction("fff", p3, u1);
		
		List<ProductReaction> prl1 = new ArrayList<>();
		//prl1.add(pr1);
		//prl1.add(pr2);
		//prl1.add(pr3);    // uncomment and u1 wont be suspicious
		
		u1.setProductReactions(prl1);
	
		kSession.insert(u1);
		kSession.insert(u2);

		kSession.insert(r1);
		kSession.insert(r2);
		kSession.insert(r3);
		
		kSession.insert(p1);
		kSession.insert(p2);
		kSession.insert(p3);
		
		
		kSession.fireAllRules();
		kSession.dispose();
	}
}
