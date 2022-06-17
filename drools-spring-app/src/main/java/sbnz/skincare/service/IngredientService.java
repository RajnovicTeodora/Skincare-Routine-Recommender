package sbnz.skincare.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sbnz.skincare.exception.NotFoundException;
import sbnz.skincare.facts.Ingredient;
import sbnz.skincare.repository.IngredientRepository;

import java.util.List;

@Service
public class IngredientService {

    private final IngredientRepository ingredientRepository;

    @Autowired
    public IngredientService(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    public List<Ingredient> getAll() {
        return this.ingredientRepository.findAll();
    }

    public Ingredient getById(Long id) {
        return this.ingredientRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(
                        String.format("Ingredient with id %d not found", id)));
    }
}
