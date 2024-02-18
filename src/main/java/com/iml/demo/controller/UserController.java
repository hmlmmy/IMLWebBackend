package com.iml.demo.controller;

import com.iml.demo.model.AuthenticationRequest;
import com.iml.demo.model.User;
import com.iml.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000") // 替换为您的前端应用的地址
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

//    @Autowired
//    private PasswordEncoder passwordEncoder;

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        // 根据用户ID获取用户信息的逻辑
        // 返回用户信息作为 JSON 响应
        // 这里假设有一个 UserService 处理业务逻辑
        User user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/save")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        // 保存用户信息的逻辑
        // 这里假设有一个 UserService 处理业务逻辑
        User savedUser = userService.saveUser(user);
        return ResponseEntity.ok(savedUser);
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User newUser) {
        try {
            // 检查用户名是否已被使用
            if (userService.isUsernameTaken(newUser.getUsername())) {
                return ResponseEntity.badRequest().body("用户名已被使用");
            }

            // 检查电子邮件是否已注册
            if (userService.isEmailRegistered(newUser.getEmail())) {
                return ResponseEntity.badRequest().body("电子邮件已注册");
            }

            // 设置新用户的默认角色（根据需要自定义）
            //newUser.setRole("USER");

            // 保存新用户
            User savedUser = userService.saveUser(newUser);

            return ResponseEntity.ok(savedUser);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("内部服务器错误");
        }
    }

    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable Long userId, @RequestBody User updatedUser) {
        // 在实际应用中，你可能需要添加验证和错误处理逻辑

        // 更新用户信息
        User existingUser = userService.getUserById(userId);
        if (existingUser == null) {
            return ResponseEntity.notFound().build();
        }

        existingUser.setUsername(updatedUser.getUsername());
        existingUser.setEmail(updatedUser.getEmail());
        // 其他更新逻辑...

        User savedUser = userService.saveUser(existingUser); // 保存更新后的用户信息

        return ResponseEntity.ok(savedUser);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequest authenticationRequest) {
        // 身份验证逻辑...

        // 假设验证成功，返回包含用户信息的对象，包括用户 ID
        User authenticatedUser = userService.getUserByUsername(authenticationRequest.getUsername());
        Map<String, Object> response = new HashMap<>();
        response.put("id", authenticatedUser.getId());
        response.put("username", authenticatedUser.getUsername());
        // 其他用户信息...

        return ResponseEntity.ok(response);
    }
}
