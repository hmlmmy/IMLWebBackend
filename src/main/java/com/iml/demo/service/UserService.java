package com.iml.demo.service;

import com.iml.demo.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    void registerUser(String username, String password);

    boolean authenticateUser(String username, String password);

    User getUserById(Long id);

    User saveUser(User user);
}
