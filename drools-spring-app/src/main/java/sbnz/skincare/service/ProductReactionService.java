package sbnz.skincare.service;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sbnz.skincare.dto.NewProductReactionDTO;
import sbnz.skincare.facts.ProductReaction;
import sbnz.skincare.facts.drools.ReactionInput;

@Service
public class ProductReactionService {

	private static Logger log = LoggerFactory.getLogger(ProductReactionService.class);

	private final KieContainer kieContainer;

	@Autowired
	public ProductReactionService(KieContainer kieContainer) {
		this.kieContainer = kieContainer;
	}

	public void checkProductReaction(NewProductReactionDTO dto) {

		KieSession kSession = kieContainer.newKieSession();

		ReactionInput advice = new ReactionInput(dto);
		ProductReaction reaction = new ProductReaction(null, null, null);

		kSession.insert(reaction);
		kSession.setGlobal("adviceInput", advice);
		kSession.fireAllRules();

		System.out.println(reaction.getReaction());

	}
}
