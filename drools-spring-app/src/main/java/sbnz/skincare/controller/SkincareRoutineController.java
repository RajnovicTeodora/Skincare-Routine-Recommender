package sbnz.skincare.controller;

import javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import sbnz.skincare.dto.RecommendedRoutineDTO;
import sbnz.skincare.dto.RequestRoutineDTO;
import sbnz.skincare.dto.RoutineDTO;
import sbnz.skincare.dto.RoutineWithReactionDTO;
import sbnz.skincare.facts.Routine;
import sbnz.skincare.service.SkincareRoutineService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/routine", produces = MediaType.APPLICATION_JSON_VALUE)
@Transactional
public class SkincareRoutineController {

    private final SkincareRoutineService skincareRoutineService;

    @Autowired
    public SkincareRoutineController(SkincareRoutineService skincareRoutineService) {
        this.skincareRoutineService = skincareRoutineService;
    }

    @PostMapping("/getRoutineRecommendation")
    public ResponseEntity<RecommendedRoutineDTO> getRoutineRecommendation(
            @RequestBody RequestRoutineDTO requestRoutineDTO) throws NotFoundException {
        Routine r = this.skincareRoutineService.getRoutineRecommendation(requestRoutineDTO);
        return new ResponseEntity<RecommendedRoutineDTO>(new RecommendedRoutineDTO(), HttpStatus.OK);
    }

    @RequestMapping(value = "/getPatientRoutines/{username}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<RoutineDTO>> findByPatientUsername(@PathVariable("username") String username) {
        List<RoutineDTO> routines = this.skincareRoutineService
                .findByPatientUsername(username)
                .stream()
                .map(RoutineDTO::new).collect(Collectors.toList());
        return new ResponseEntity<>(routines, HttpStatus.OK);
    }

    @RequestMapping(value = "/getPatientRoutinesWithReaction/{username}",
            method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<RoutineWithReactionDTO>> findByPatientUsernameWithReaction
            (@PathVariable("username") String username) {

        return new ResponseEntity<>
                (this.skincareRoutineService.findPatientRoutinesWithReaction(username), HttpStatus.OK);

    }
}