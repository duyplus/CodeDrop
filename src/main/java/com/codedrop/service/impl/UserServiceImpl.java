package com.codedrop.service.impl;

import com.codedrop.exception.ResourceNotFoundException;
import com.codedrop.model.Authority;
import com.codedrop.model.Role;
import com.codedrop.model.User;
import com.codedrop.repository.AuthorityRepository;
import com.codedrop.repository.RoleRepository;
import com.codedrop.repository.UserRepository;
import com.codedrop.service.UserService;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder pe;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Page<User> findPaginate(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return userRepository.findAll(pageable);
    }

    public Page<User> findPaginateWithConditions(int page, int size, Map<String, String> conditions) {
        Pageable pageable = PageRequest.of(page, size);
        Specification<User> specification = createSpecification(conditions);
        return userRepository.findAll(specification, pageable);
    }

    private Specification<User> createSpecification(Map<String, String> conditions) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            for (Map.Entry<String, String> entry : conditions.entrySet()) {
                String field = entry.getKey();
                String value = entry.getValue();

                predicates.add(criteriaBuilder.like(
                        criteriaBuilder.lower(root.get(field)),
                        "%" + value.toLowerCase() + "%"
                ));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

    @Override
    public User findById(Integer id) {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));
    }

    @Override
    public User create(User user) {
        return userRepository.save(user);
    }

    @Override
    public User update(User user) {
        return userRepository.save(user);
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    public User findUsernameByEmail(String email) {
        return userRepository.findUsernameByEmail(email);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User findByToken(String token) {
        return userRepository.findByToken(token);
    }

    @Transactional
    public void saveOrUpdateOAuthUser(String email, String username, String fullname, String photo) {
        User existing = userRepository.findByEmail(email);
        if (existing == null) {
            User user = new User();
            user.setEmail(email);
            user.setUsername(username);
            user.setPassword(pe.encode("123456"));
            user.setFullname(fullname);
            user.setPhoto(photo);
            user.setCreatedAt(new Date());
            user.setUpdatedAt(new Date());
            user.setIsDelete(false);
            user = userRepository.save(user);
            Role userRole = roleRepository.findByName("USER");
            if (userRole != null) {
                Authority authority = new Authority();
                authority.setRole(userRole);
                authority.setUser(user);
                authorityRepository.save(authority);
            }
        } else {
            existing.setUpdatedAt(new Date());
            userRepository.save(existing);
        }
    }
}
