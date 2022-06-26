package sbnz.skincare.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import sbnz.skincare.dto.NewProductReactionDTO;
import sbnz.skincare.dto.ProductReactionDTO;
import sbnz.skincare.facts.ProductReaction;
import sbnz.skincare.service.ProductReactionService;

@RestController
@Transactional
@RequestMapping(value = "/reaction", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductReactionController {

    private final ProductReactionService productReactionService;

    @Autowired
    public ProductReactionController(ProductReactionService productReactionService) {
        this.productReactionService = productReactionService;
    }

    @PostMapping("/checkProductReaction")
    public ResponseEntity<ProductReactionDTO> checkProductReaction(
            @RequestBody NewProductReactionDTO newProductReactionDTO) {
        ProductReaction reaction = this.productReactionService.checkProductReaction(newProductReactionDTO);
        if (reaction == null)
            return new ResponseEntity<>(null, HttpStatus.OK);
        return new ResponseEntity<>(new ProductReactionDTO(reaction), HttpStatus.OK);
    }

    @RequestMapping(value = "/hasReaction/{username}/{product}",
            method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Boolean> hasReaction(
            @PathVariable(value = "username") String username, @PathVariable(value = "product") Long product) {
        return new ResponseEntity<>(
                this.productReactionService.findByProductAndPatient(product, username) != null, HttpStatus.OK);
    }
}
