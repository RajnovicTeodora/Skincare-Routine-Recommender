package sbnz.skincare.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sbnz.skincare.facts.SkinTypeCharacteristics;

@Repository
public interface SkinTypeCharacteristicsRepository extends JpaRepository<SkinTypeCharacteristics, Long> {
	
	List<SkinTypeCharacteristics> findAll();

}
