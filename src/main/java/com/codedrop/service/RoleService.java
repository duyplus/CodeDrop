package com.codedrop.service;

import com.codedrop.model.Role;

import java.util.List;

public interface RoleService {

    List<Role> findAll();

    Role findById(String id);

    Role create(Role role);

    Role update(Role role);

    void delete(Role role);
}
