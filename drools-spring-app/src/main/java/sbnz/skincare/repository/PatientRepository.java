package sbnz.skincare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sbnz.skincare.facts.Patient;
import sbnz.skincare.facts.User;

import java.util.List;
import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    Optional<Patient> findById(@Param("id") Long id);

    Optional<Patient>  findByUsername(@Param("username") String username);

    @Query("SELECT p FROM Patient p WHERE" +
            "((lower(p.name) like lower(concat('%', :search,'%'))) or " +
            "(lower(p.email) like lower(concat('%', :search,'%'))) or" +
            "(lower(p.username) like lower(concat('%', :search,'%'))) or" +
            "(lower(p.surname) like lower(concat('%', :search,'%'))))")
    List<Patient> findAllAndSearch(@Param("search") String search);

}
