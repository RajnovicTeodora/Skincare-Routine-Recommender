package sbnz.skincare.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sbnz.skincare.dto.NewPatientDTO;
import sbnz.skincare.exception.NotFoundException;
import sbnz.skincare.exception.UsernameTakenException;
import sbnz.skincare.facts.Patient;
import sbnz.skincare.facts.UserRole;
import sbnz.skincare.repository.PatientRepository;
import sbnz.skincare.repository.UserRoleRepository;

import java.util.List;

@Service
public class PatientService {

    private static Logger log = LoggerFactory.getLogger(PatientService.class);

    private final UserRoleRepository userRoleRepository;

    private final PatientRepository patientRepository;

    private final UserService userService;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public PatientService(PatientRepository patientRepository, UserService userService,
                          UserRoleRepository userRoleRepository, PasswordEncoder passwordEncoder) {
        this.patientRepository = patientRepository;
        this.userService = userService;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Patient> getAll() {
        return this.patientRepository.findAll();
    }

    public Patient findByUsername(String username) {
        return patientRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException(
                        String.format("Patient with username %s not found", username)));
    }

    public Patient register(NewPatientDTO dto) {
        if (userService.findByUsername(dto.getUsername()) != null)
            throw new UsernameTakenException("Username already taken!");

        UserRole role = this.userRoleRepository.findByName("PATIENT").orElseThrow(NotFoundException::new);
        Patient patient = new Patient(dto, role);

        patient.setPassword(passwordEncoder.encode(dto.getPassword()));
        patient.setRole(role);

        save(patient);
        return patient;
    }

    public Patient save(Patient patient) {
        return this.patientRepository.save(patient);
    }
}
