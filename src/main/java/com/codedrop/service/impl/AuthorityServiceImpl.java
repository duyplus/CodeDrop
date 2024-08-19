package com.codedrop.service.impl;

import com.codedrop.model.Authority;
import com.codedrop.repository.AuthorityRepository;
import com.codedrop.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorityServiceImpl implements AuthorityService {

    @Autowired
    private AuthorityRepository authorityRepository;

    @Override
    public List<Authority> findAll() {
        return authorityRepository.findAll();
    }

    @Override
    public Authority findById(Integer id) {
        return authorityRepository.findById(id).orElseThrow(() -> new RuntimeException("Authority not found with id " + id));
    }

    @Override
    public Authority create(Authority authority) {
        return authorityRepository.save(authority);
    }

    @Override
    public void delete(Authority authority) {
        authorityRepository.delete(authority);
    }

}
