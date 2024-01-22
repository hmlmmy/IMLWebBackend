package com.iml.demo.controller;

import com.iml.demo.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public void register(@RequestParam String username, @RequestParam String password) {
        userService.registerUser(username, password);
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        boolean authenticated = userService.authenticateUser(username, password);
        return authenticated ? "Login successful" : "Login failed";
    }
}

