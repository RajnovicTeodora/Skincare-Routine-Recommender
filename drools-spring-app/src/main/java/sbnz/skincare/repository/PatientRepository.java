package sbnz.skincare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import sbnz.skincare.facts.Patient;

import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    Optional<Patient> findById(@Param("id") Long id);

    Optional<Patient>  findByUsername(@Param("username") String username);

}
