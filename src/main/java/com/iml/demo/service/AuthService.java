package com.iml.demo.service;

import com.iml.demo.model.AuthenticationRequest;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<?> authenticate(AuthenticationRequest authenticationRequest);

    boolean authenticateUser(String username, String password);
}

