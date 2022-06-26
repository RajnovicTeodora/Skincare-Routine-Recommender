package sbnz.skincare.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sbnz.skincare.dto.IngredientDTO;
import sbnz.skincare.service.IngredientService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/ingredients", produces = MediaType.APPLICATION_JSON_VALUE)
@Transactional
public class IngredientController {

    private final IngredientService ingredientService;

    @Autowired
    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<IngredientDTO>> getAll() {
        List<IngredientDTO> ingredient = this.ingredientService.getAll()
                .stream()
                .map(IngredientDTO::new).collect(Collectors.toList());

        return new ResponseEntity<>(ingredient, HttpStatus.OK);
    }
}
