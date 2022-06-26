package sbnz.skincare.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sbnz.skincare.dto.NewProductDTO;
import sbnz.skincare.exception.NotFoundException;
import sbnz.skincare.exception.ProductExistsException;
import sbnz.skincare.facts.Ingredient;
import sbnz.skincare.facts.Product;
import sbnz.skincare.repository.ProductRepository;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    private final IngredientService ingredientService;

    @Autowired
    public ProductService(ProductRepository productRepository, IngredientService ingredientService) {
        this.productRepository = productRepository;
        this.ingredientService = ingredientService;
    }

    public List<Product> getAll() {
        return this.productRepository.findAll();
    }

    public Product addProduct(@NotEmpty NewProductDTO dto) {

        Optional<Product> maybeProduct = productRepository
                .findByNameAndProductTypeAndManufacturer(dto.getName(), dto.getProductType(), dto.getManufacturer());
        if (maybeProduct.isPresent())
            throw new ProductExistsException(String.format("Product with name %s already exists!", dto.getName()));

        List<Ingredient> ingredients = new ArrayList<>();

        for (Long ingredientId : dto.getIngredients()) { // TODO Enable adding generic ingredients?
            ingredients.add(ingredientService.getById(ingredientId));
        }

        Product product = new Product(dto, ingredients);
        return productRepository.save(new Product(dto, ingredients));
    }

    public Product findById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(
                        String.format("Product with id %d not found", id)));
    }
}
