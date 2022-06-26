package sbnz.skincare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sbnz.skincare.facts.Reaction;

public interface ReactionRepository extends JpaRepository<Reaction, Long> {


}