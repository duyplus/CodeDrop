package com.codedrop.repository;

import com.codedrop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("SELECT DISTINCT a.user FROM Authority a WHERE a.role.id IN('ADMIN')")
    List<User> getAdministrators();

    @Query("SELECT u FROM User u WHERE u.email = ?1")
    User findUsernameByEmail(String email);

    User findByUsername(String username);

    User findByEmail(String email);

    User findByToken(String token);
}