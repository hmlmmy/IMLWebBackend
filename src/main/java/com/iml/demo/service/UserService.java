package com.iml.demo.service;

import com.iml.demo.model.AuthenticationRequest;
import com.iml.demo.model.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    //void registerUser(String username, String password);

    void registerUser(String username, String password, String email);

    boolean authenticateUser(String username, String password);

    User getUserById(Long id);

    User saveUser(User user);

    ResponseEntity<?> authenticate(AuthenticationRequest authenticationRequest);

    boolean isUsernameTaken(String username);

    boolean isEmailRegistered(String email);

    User getUserByUsername(String username);
}
