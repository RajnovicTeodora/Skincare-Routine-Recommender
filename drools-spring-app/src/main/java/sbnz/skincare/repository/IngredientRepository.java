package sbnz.skincare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sbnz.skincare.facts.Ingredient;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {


}

