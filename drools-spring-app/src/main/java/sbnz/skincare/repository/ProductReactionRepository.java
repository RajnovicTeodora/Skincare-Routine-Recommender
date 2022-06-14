package sbnz.skincare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import sbnz.skincare.facts.ProductReaction;

import java.util.Optional;

public interface ProductReactionRepository extends JpaRepository<ProductReaction, Long> {

    Optional<ProductReaction> findByProductIdAndPatientUsername(@Param("id") Long id, @Param("username") String username);
}
