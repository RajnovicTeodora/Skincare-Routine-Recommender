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

        ReactionInput advice = new ReactionInput("Bad reaction", dto.getSymptom());
        ProductReaction reaction = new ProductReaction(null, null, null);

        kSession.insert(reaction);
        kSession.insert(advice);
        
        kSession.fireAllRules();
        kSession.dispose();

        if(reaction.getReaction() == null)
            return null;

        reaction.setProduct(product);
        reaction.setPatient(patient);

        patient.getProductReactions().add(reaction);
        patientService.save(patient);

        return productReactionRepository.save(reaction);
    }

    public ProductReaction findByProductAndPatient(Long id, String username) {
        return this.productReactionRepository.findByProductIdAndPatientUsername(id, username).orElse(null);
    }

    public List<ProductReaction> getAll() {
        return this.productReactionRepository.findAll();
    }
}
