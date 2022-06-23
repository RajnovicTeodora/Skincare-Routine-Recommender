package sbnz.skincare.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sbnz.skincare.dto.PatientDTO;
import sbnz.skincare.dto.ProductReportDTO;
import sbnz.skincare.service.ReportService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Transactional
@RequestMapping(value = "/report", produces = MediaType.APPLICATION_JSON_VALUE)
public class ReportController {

    private final ReportService reportService;

    @Autowired
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/getFrequentRoutineChangeReport")
    public ResponseEntity<List<PatientDTO>> getFrequentRoutineChangeReport() {
        List<PatientDTO> patients = this.reportService.getFrequentRoutineChangeReport()
                .stream()
                .map(PatientDTO::new).collect(Collectors.toList());
        this.reportService.getFrequentRoutineChangeReport();
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }

    @GetMapping("/getProductReport")
    public ResponseEntity<ProductReportDTO> getProductReport() {
        return new ResponseEntity<>(new ProductReportDTO(this.reportService.getBestAndWorstProduct()), HttpStatus.OK);
    }

}
