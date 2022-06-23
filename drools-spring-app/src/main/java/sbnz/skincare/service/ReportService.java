package sbnz.skincare.service;

import java.util.ArrayList;
import java.util.List;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sbnz.skincare.facts.Patient;
import sbnz.skincare.facts.Product;
import sbnz.skincare.facts.Routine;
import sbnz.skincare.facts.drools.ProductReport;

@Service
public class ReportService {

    private final KieContainer kieContainer;

    private final PatientService patientService;

    private final ProductService productService;

    private final SkincareRoutineService skincareRoutineService;

    @Autowired
    public ReportService(KieContainer kieContainer, PatientService patientService, ProductService productService,
                         SkincareRoutineService skincareRoutineService) {
        this.kieContainer = kieContainer;
        this.patientService = patientService;
        this.productService = productService;
        this.skincareRoutineService = skincareRoutineService;
    }

    public List<Patient> getFrequentRoutineChangeReport() {
        KieSession kSession = kieContainer.newKieSession("report_rules");

        // Insert all patients
        for(Patient patient : patientService.getAll()) {
            kSession.insert(patient);
        }

        List<Patient> susPatients = new ArrayList<>();
        kSession.setGlobal("susPatients", susPatients);

        kSession.fireAllRules();
        kSession.dispose();
        return susPatients;
    }

    public ProductReport getBestAndWorstProduct(){
        KieSession kSession = kieContainer.newKieSession("report_rules");

        // Insert all products
        for(Product product : productService.getAll()) {
            kSession.insert(product);
            System.out.println(product.getName());
        }

        // Insert all routines
        for(Routine routine : skincareRoutineService.getAll()) {
            kSession.insert(routine);
            for(Product p : routine.getProducts()){
                System.out.println(p.getName());
            }
        }

        ProductReport report = new ProductReport();
        kSession.setGlobal("productReport", report);

        kSession.fireAllRules();
        kSession.dispose();
        return report;
    }
}
