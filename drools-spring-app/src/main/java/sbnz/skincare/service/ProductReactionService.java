package sbnz.skincare.service;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sbnz.skincare.dto.NewProductReactionDTO;
import sbnz.skincare.dto.ProductReactionDTO;
import sbnz.skincare.facts.ProductReaction;
import sbnz.skincare.facts.Reaction;
import sbnz.skincare.facts.drools.ReactionInput;
import sbnz.skincare.repository.ProductReactionRepository;

@Service
public class ProductReactionService {

    private static Logger log = LoggerFactory.getLogger(ProductReactionService.class);

    private final KieContainer kieContainer;

    private final ProductReactionRepository productReactionRepository;

    @Autowired
    public ProductReactionService(KieContainer kieContainer,
                                  ProductReactionRepository productReactionRepository) {
        this.kieContainer = kieContainer;
        this.productReactionRepository = productReactionRepository;
    }

    public void checkProductReaction(NewProductReactionDTO dto) {

        KieSession kSession = kieContainer.newKieSession();

        Reaction r1 = new Reaction("Bad reaction", "Alergy");
        Reaction r2 = new Reaction("Bad reaction", "No progress");
        Reaction r3 = new Reaction("Alergy", "Anaphylaxis");
        Reaction r4 = new Reaction("Alergy", "Rash");
        Reaction r5 = new Reaction("No progress", "Condition worsening");
        Reaction r6 = new Reaction("No progress", "Breakouts");
        Reaction r7 = new Reaction("Anaphylaxis", "Difficulty breathing");
        Reaction r8 = new Reaction("Anaphylaxis", "Swelling");
        Reaction r9 = new Reaction("Rash", "Severe itching");
        Reaction r10 = new Reaction("Rash", "Red patches");

        ReactionInput advice = new ReactionInput(dto);
        ProductReaction reaction = new ProductReaction(null, null, null);

        kSession.insert(reaction);
        kSession.insert(advice);
        kSession.insert(r1);
        kSession.insert(r2);
        kSession.insert(r3);
        kSession.insert(r4);
        kSession.insert(r5);
        kSession.insert(r6);
        kSession.insert(r7);
        kSession.insert(r8);
        kSession.insert(r9);
        kSession.insert(r10);
        kSession.fireAllRules();
        kSession.dispose();
        System.out.println(reaction.getReaction());

    }

    public ProductReaction findByProductAndPatient(Long id, String username) {
        return this.productReactionRepository.findByProductIdAndPatientUsername(id, username).orElse(null);
    }
}
