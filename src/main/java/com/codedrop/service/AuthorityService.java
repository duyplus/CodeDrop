package com.codedrop.service;

import com.codedrop.model.Authority;
import org.springframework.stereotype.Component;

import java.util.List;

public interface AuthorityService {

    List<Authority> findAll();

    Authority findById(Integer id);

    Authority create(Authority authority);

    void delete(Authority authority);
}
