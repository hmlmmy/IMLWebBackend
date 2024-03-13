package com.iml.demo.serviceImpl;

import com.iml.demo.model.AuthenticationRequest;
import com.iml.demo.model.User;
import com.iml.demo.service.AuthService;
import com.iml.demo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserService userService;

    public AuthServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ResponseEntity<?> authenticate(AuthenticationRequest authenticationRequest) {
        // 实现身份验证逻辑...
        // 使用userService进行用户验证
        User authenticatedUser = userService.getUserByUsername(authenticationRequest.getUsername());

        if (authenticatedUser == null) {
            return ResponseEntity.notFound().build();
        }

        if (!authenticateUser(authenticatedUser.getUsername(), authenticationRequest.getPassword())) {
            return ResponseEntity.status(401).body("Username and password do not match");
        }

        // 构建包含用户信息的响应
        Map<String, Object> response = new HashMap<>();
        response.put("id", authenticatedUser.getId());
        response.put("username", authenticatedUser.getUsername());
        response.put("email", authenticatedUser.getEmail());
        // 其他用户信息...

        return ResponseEntity.ok(response);
    }

    @Override
    public boolean authenticateUser(String username, String password) {
        // 实现用户身份验证逻辑...
        User user = userService.getUserByUsername(username);

        return user != null && user.getPassword().equals(password);
    }
}

