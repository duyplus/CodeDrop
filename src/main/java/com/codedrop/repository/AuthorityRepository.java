package com.codedrop.repository;

import com.codedrop.model.Authority;
import com.codedrop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Integer> {

    @Query("SELECT DISTINCT a FROM Authority a WHERE a.user IN ?1")
    List<Authority> authoritiesOf(List<User> customers);
}