package sbnz.skincare.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sbnz.skincare.dto.NewPatientDTO;
import sbnz.skincare.dto.PatientDTO;
import sbnz.skincare.facts.Patient;
import sbnz.skincare.service.PatientService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/patients", produces = MediaType.APPLICATION_JSON_VALUE)
public class PatientController {

    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<PatientDTO>> getAll() {
        List<PatientDTO> patients = this.patientService.getAll()
                .stream()
                .map(PatientDTO::new).collect(Collectors.toList());

        return new ResponseEntity<>(patients, HttpStatus.OK);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<PatientDTO> register(@RequestBody NewPatientDTO dto) {
        return new ResponseEntity<>(new PatientDTO(this.patientService.register(dto)), HttpStatus.OK);
    }
}
