package sbnz.skincare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import sbnz.skincare.facts.Product;
import sbnz.skincare.facts.enumerations.ProductType;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findByNameAndProductTypeAndManufacturer(@Param("name") String name,
                                                              @Param("productType") ProductType productType,
                                                              @Param("manufacturer") String manufacturer);
}
