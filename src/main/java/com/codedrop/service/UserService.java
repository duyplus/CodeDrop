package com.codedrop.service;

import com.codedrop.model.User;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface UserService {

    List<User> findAll();

    Page<User> findPaginate(int page, int size);

    Page<User> findPaginateWithConditions(int page, int size, Map<String, String> conditions);

    User findById(Integer id);

    User create(User user);

    User update(User user);

    void delete(User user);

    User findUsernameByEmail(String email);

    User findByEmail(String email);

    User findByToken(String token);

    void saveOrUpdateOAuthUser(String email, String username, String fullname, String photo);
}
