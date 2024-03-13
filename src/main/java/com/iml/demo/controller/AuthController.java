package com.iml.demo.controller;

import com.iml.demo.model.AuthenticationRequest;
import com.iml.demo.service.AuthService;
import com.iml.demo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

//    @PostMapping("/register")
//    public void register(@RequestParam String username, @RequestParam String password, @RequestParam String email) {
//        userService.registerUser(username, password, email);
//    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationRequest authenticationRequest) {
        return authService.authenticate(authenticationRequest);
    }
}


