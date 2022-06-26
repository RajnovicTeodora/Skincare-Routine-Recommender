package sbnz.skincare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import sbnz.skincare.facts.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(@Param("username") String username);

    Optional<User> findByEmail(@Param("email") String email);

    List<User> findAllByUsernameNotLike(@Param("username") String username);

    @Query("SELECT u FROM User u WHERE u.username <> :username AND" +
            "(u.role.name = :filter OR :filter = '') AND" +
            "((lower(u.name) like lower(concat('%', :search,'%'))) or " +
            "(lower(u.email) like lower(concat('%', :search,'%'))) or" +
            "(lower(u.username) like lower(concat('%', :search,'%'))) or" +
            "(lower(u.surname) like lower(concat('%', :search,'%'))))")
    List<User> findAllByUsernameNotLikeAndSearch(@Param("username") String username, @Param("search") String search,
                                                 @Param("filter") String filter);

}

