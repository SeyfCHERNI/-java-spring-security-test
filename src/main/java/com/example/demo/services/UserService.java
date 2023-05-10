package com.example.demo.services;

import org.springframework.stereotype.Service;

import com.example.demo.entities.User;


@Service
public interface UserService {

    User save(User user);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    User findByUsername(String username);
}
