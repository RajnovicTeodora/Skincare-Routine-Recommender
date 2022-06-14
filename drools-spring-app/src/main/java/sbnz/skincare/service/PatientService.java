package sbnz.skincare.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sbnz.skincare.facts.Patient;
import sbnz.skincare.repository.PatientRepository;

import java.util.List;

@Service
public class PatientService {

    private static Logger log = LoggerFactory.getLogger(ReportService.class);

    private final PatientRepository patientRepository;

    @Autowired
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<Patient> getAll() {
        return this.patientRepository.findAll();
    }
}
