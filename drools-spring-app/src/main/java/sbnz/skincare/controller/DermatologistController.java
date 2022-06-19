package sbnz.skincare.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sbnz.skincare.dto.NewUserDTO;
import sbnz.skincare.dto.UserDTO;
import sbnz.skincare.service.DermatologistService;

@RestController
@RequestMapping(value = "/dermatologists", produces = MediaType.APPLICATION_JSON_VALUE)
public class DermatologistController {

    private final DermatologistService dermatologistService;

    @Autowired
    public DermatologistController(DermatologistService dermatologistService) {
        this.dermatologistService = dermatologistService;
    }
    
    @RequestMapping(value = "/register", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<UserDTO> register(@RequestBody NewUserDTO dto) {
        return new ResponseEntity<>(new UserDTO(this.dermatologistService.register(dto)), HttpStatus.OK);
    }
}
