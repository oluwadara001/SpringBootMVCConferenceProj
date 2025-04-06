package com.YomiOluwadara.conferencedemo.services;

import java.util.List;

import com.YomiOluwadara.conferencedemo.models.User;

public interface UserService {
    List<User> findAll();
    User findById(Long id);
    User save(User user);
    void deleteById(Long id);
    User findByEmail(String email);
} 