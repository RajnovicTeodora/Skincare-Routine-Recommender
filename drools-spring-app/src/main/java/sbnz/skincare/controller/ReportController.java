package sbnz.skincare.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sbnz.skincare.service.ReportService;

@RestController
@RequestMapping(value = "/report", produces = MediaType.APPLICATION_JSON_VALUE)
public class ReportController {

	private ReportService reportService;

	@Autowired
	public ReportController(ReportService reportService) {
		this.reportService = reportService;
	}

	@GetMapping("/getFrequentRoutineChangeReport")
	public ResponseEntity<?> getFrequentRoutineChangeReport() {
		this.reportService.getFrequentRoutineChangeReport();
		return new ResponseEntity<>(null, HttpStatus.OK);
	}
}
