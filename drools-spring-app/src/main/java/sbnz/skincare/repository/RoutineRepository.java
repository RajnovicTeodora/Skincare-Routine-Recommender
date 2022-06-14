package sbnz.skincare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import sbnz.skincare.facts.Routine;

import java.util.List;

public interface RoutineRepository extends JpaRepository<Routine, Long> {

    List<Routine> findByPatientUsername(@Param("username") String username);

}
