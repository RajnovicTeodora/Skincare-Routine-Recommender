package sbnz.skincare.service;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sbnz.skincare.dto.NewProductReactionDTO;
import sbnz.skincare.facts.Patient;
import sbnz.skincare.facts.Product;
import sbnz.skincare.facts.ProductReaction;
import sbnz.skincare.facts.Reaction;
import sbnz.skincare.facts.drools.ReactionInput;
import sbnz.skincare.repository.ProductReactionRepository;
import sbnz.skincare.repository.ReactionRepository;

import java.util.List;

@Service
public class ProductReactionService {

    private final KieContainer kieContainer;

    private final ProductReactionRepository productReactionRepository;

    private final ReactionRepository reactionRepository; // Todo service ?

    private final PatientService patientService;

    private final ProductService productService;

    @Autowired
    public ProductReactionService(KieContainer kieContainer, ProductReactionRepository productReactionRepository,
                                  ReactionRepository reactionRepository, PatientService patientService,
                                  ProductService productService) {
        this.kieContainer = kieContainer;
        this.productReactionRepository = productReactionRepository;
        this.reactionRepository = reactionRepository;
        this.patientService = patientService;
        this.productService = productService;
    }

    public ProductReaction checkProductReaction(NewProductReactionDTO dto) {

        Patient patient = this.patientService.findByUsername(dto.getUsername());
        Product product = this.productService.findById(dto.getProductId());

        KieSession kSession = kieContainer.newKieSession("reaction_rules");

        for (Reaction reaction : this.reactionRepository.findAll()) {
            kSession.insert(reaction);
        }

        /*
        Reaction r1 = new Reaction("Bad reaction", "Allergy");
        Reaction r2 = new Reaction("Bad reaction", "No progress");
        Reaction r3 = new Reaction("Alergy", "Anaphylaxis");
        Reaction r4 = new Reaction("Alergy", "Rash");
        Reaction r5 = new Reaction("No progress", "Condition worsening");
        Reaction r6 = new Reaction("No progress", "Breakouts");
        Reaction r7 = new Reaction("Anaphylaxis", "Difficulty breathing");
        Reaction r8 = new Reaction("Anaphylaxis", "Swelling");
        Reaction r9 = new Reaction("Rash", "Severe itching");
        Reaction r10 = new Reaction("Rash", "Red patches");
        */
        ReactionInput advice = new ReactionInput("Bad reaction", dto.getSymptom());
        ProductReaction reaction = new ProductReaction(null, null, null);

        kSession.insert(reaction);
        kSession.insert(advice);

        /*kSession.insert(r1);
        kSession.insert(r2);
        kSession.insert(r3);
        kSession.insert(r4);
        kSession.insert(r5);
        kSession.insert(r6);
        kSession.insert(r7);
        kSession.insert(r8);
        kSession.insert(r9);
        kSession.insert(r10);*/
        kSession.fireAllRules();
        kSession.dispose();

        if(reaction.getReaction() == null)
            return null;

        reaction.setProduct(product);
        reaction.setPatient(patient);

        patient.getProductReactions().add(reaction);
        patientService.save(patient);

        System.out.println(reaction.getReaction());

        return productReactionRepository.save(reaction);
    }

    public ProductReaction findByProductAndPatient(Long id, String username) {
        return this.productReactionRepository.findByProductIdAndPatientUsername(id, username).orElse(null);
    }

    public List<ProductReaction> getAll() {
        return this.productReactionRepository.findAll();
    }
}
