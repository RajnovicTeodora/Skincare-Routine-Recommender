package sbnz.skincare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sbnz.skincare.facts.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
