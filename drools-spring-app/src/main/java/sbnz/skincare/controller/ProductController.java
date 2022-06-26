package sbnz.skincare.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sbnz.skincare.dto.NewProductDTO;
import sbnz.skincare.dto.ProductWithIngredientsDTO;
import sbnz.skincare.service.ProductService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/products", produces = MediaType.APPLICATION_JSON_VALUE)
@Transactional
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<ProductWithIngredientsDTO>> getAll() {
        List<ProductWithIngredientsDTO> products = this.productService.getAll()
                .stream()
                .map(ProductWithIngredientsDTO::new).collect(Collectors.toList());

        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @RequestMapping(value = "/addProduct", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<ProductWithIngredientsDTO> addProduct(@RequestBody NewProductDTO newProductDTO) {

        return new ResponseEntity<>(
                new ProductWithIngredientsDTO(this.productService.addProduct(newProductDTO)), HttpStatus.OK);
    }
}
