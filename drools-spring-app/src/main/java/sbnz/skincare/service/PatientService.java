package sbnz.skincare.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sbnz.skincare.exception.NotFoundException;
import sbnz.skincare.facts.Patient;
import sbnz.skincare.repository.PatientRepository;

import java.util.List;

@Service
public class PatientService {

    private static Logger log = LoggerFactory.getLogger(PatientService.class);

    private final PatientRepository patientRepository;

    @Autowired
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<Patient> getAll() {
        return this.patientRepository.findAll();
    }

    public Patient findByUsername(String username) {
        return patientRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException(
                        String.format("Patient with username %s not found", username)));
    }

    public Patient save(Patient patient){
        return this.patientRepository.save(patient);
    }
}
