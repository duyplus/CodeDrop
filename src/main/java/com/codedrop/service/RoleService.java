package com.codedrop.service;

import com.codedrop.model.Role;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface RoleService {

    List<Role> findAll();

    Page<Role> findPaginate(int page, int size);

    Page<Role> findPaginateWithConditions(int page, int size, Map<String, String> conditions);

    Role findById(String id);

    Role create(Role role);

    Role update(Role role);

    void delete(Role role);
}
