package com.codedrop.rest;

import com.codedrop.entity.Role;
import com.codedrop.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/roles")
public class RoleRestController {

    @Autowired
    RoleService roleService;

    @GetMapping
    public List<Role> getAll() {
        return roleService.findAll();
    }

}
