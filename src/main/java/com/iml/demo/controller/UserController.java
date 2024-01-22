package com.iml.demo.controller;

import com.iml.demo.model.User;
import com.iml.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000") // 替换为您的前端应用的地址
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        // 根据用户ID获取用户信息的逻辑
        // 返回用户信息作为 JSON 响应
        // 这里假设有一个 UserService 处理业务逻辑
        User user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

//    @GetMapping("/test")
//    public User getUserStatus(){
//        User user = new User((long)1,"a","aa");
//        System.out.println(user);
//        return user;
//    }

    @PostMapping("/save")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        // 保存用户信息的逻辑
        // 这里假设有一个 UserService 处理业务逻辑
        User savedUser = userService.saveUser(user);
        return ResponseEntity.ok(savedUser);
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
}

