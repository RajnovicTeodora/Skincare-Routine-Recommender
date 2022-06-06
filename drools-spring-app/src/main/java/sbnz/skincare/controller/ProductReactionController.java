package sbnz.skincare.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sbnz.skincare.dto.NewProductReactionDTO;
import sbnz.skincare.service.ProductReactionService;

@RestController
@RequestMapping(value = "/product/reaction", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductReactionController {

	private ProductReactionService productReactionService;

	@Autowired
	public ProductReactionController(ProductReactionService productReactionService) {
		this.productReactionService = productReactionService;
	}

	@PostMapping("/checkProductReaction")
	public ResponseEntity<?> checkProductReaction(
			@RequestBody NewProductReactionDTO newProductReactionDTO) {
		this.productReactionService.checkProductReaction(newProductReactionDTO);
		return new ResponseEntity<>(null, HttpStatus.OK);
	}
}
