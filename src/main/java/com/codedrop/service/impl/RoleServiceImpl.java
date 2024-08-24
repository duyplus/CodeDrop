package com.codedrop.service.impl;

import com.codedrop.common.CustomSpecification;
import com.codedrop.model.Role;
import com.codedrop.repository.RoleRepository;
import com.codedrop.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository roleRepository;

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    public Page<Role> findPaginate(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return roleRepository.findAll(pageable);
    }

    public Page<Role> findPaginateWithConditions(int page, int size, Map<String, String> conditions) {
        Pageable pageable = PageRequest.of(page, size);
        Specification<Role> specification = CustomSpecification.createSpecification(conditions);
        return roleRepository.findAll(specification, pageable);
    }

    @Override
    public Role findById(String id) {
        return roleRepository.findById(id).orElseThrow(() -> new RuntimeException("Role not found with id " + id));
    }

    @Override
    public Role create(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role update(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public void delete(Role role) {
        roleRepository.delete(role);
    }
}
