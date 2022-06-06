package sbnz.skincare.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sbnz.skincare.dto.RecommendedRoutineDTO;
import sbnz.skincare.dto.RequestRoutineDTO;
import sbnz.skincare.facts.Routine;
import sbnz.skincare.service.SkincareRoutineService;

@RestController
@RequestMapping(value = "/routine", produces = MediaType.APPLICATION_JSON_VALUE)
public class SkincareRoutineController {

	private SkincareRoutineService skincareRoutineService;

	@Autowired
	public SkincareRoutineController(SkincareRoutineService skincareRoutineService) {
		this.skincareRoutineService = skincareRoutineService;
	}

	@PostMapping("/getRoutineRecommendation")
	public ResponseEntity<RecommendedRoutineDTO> getRoutineRecommendation(
			@RequestBody RequestRoutineDTO requestRoutineDTO) {
		Routine r = this.skincareRoutineService.getRoutineRecommendation(requestRoutineDTO);
		return new ResponseEntity<RecommendedRoutineDTO>(new RecommendedRoutineDTO(), HttpStatus.OK);
	}

}
