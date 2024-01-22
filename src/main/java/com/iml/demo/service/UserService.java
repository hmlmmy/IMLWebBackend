package com.iml.demo.service;

import com.iml.demo.model.User;
import com.iml.demo.model.UserRepository;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
//    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
//        this.passwordEncoder = passwordEncoder;
    }

    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }
    public void registerUser(String username, String password) {
        User user = new User();
        user.setUsername(username);
//        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
    }

    public boolean authenticateUser(String username, String password) {
        User user = userRepository.findByUsername(username);
//        return user != null && passwordEncoder.matches(password, user.getPassword());
        return true;
    }

    @GetMapping("/{id}")
    public User getUserById(Long id) {
        // 使用JPA的findById方法获取用户信息
        // 返回一个Optional<User>，orElse方法用于处理用户不存在的情况
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.orElse(null);
    }

    @PostMapping("/save")
    public User saveUser(User user) {
        // 使用JPA的save方法保存用户信息
        return userRepository.save(user);
    }
}

