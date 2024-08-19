package com.codedrop.service.impl;

import com.codedrop.dto.UserDto;
import com.codedrop.model.User;
import com.codedrop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserDetailsImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder pe;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("UserDetails not found with id: " + username);
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), new ArrayList<>());
    }

    public User save(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setUserCode(userDto.getUserCode());
        user.setEmail(userDto.getEmail());
        user.setUsername(userDto.getUsername());
        user.setPassword(pe.encode(userDto.getPassword()));
        user.setFullname(userDto.getFullname());
        user.setPhone(userDto.getPhone());
        user.setPhoto(userDto.getPhoto());
        user.setToken(userDto.getToken());
        user.setCreatedAt(userDto.getCreatedAt());
        user.setUpdatedAt(userDto.getUpdatedAt());
        user.setIsDelete(userDto.getIsDelete());
        return userRepository.save(user);
    }
}