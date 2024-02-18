package com.iml.demo.serviceImpl;

import com.iml.demo.model.AuthenticationRequest;
import com.iml.demo.model.User;
import com.iml.demo.repository.UserRepository;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.iml.demo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

import static com.iml.demo.RandomIdGenerator.generateRandomId;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
//    private final BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
//        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public void registerUser(String username, String password, String email) {
        // 生成随机8位ID
        long randomId = generateRandomId();

        User user = new User();
        user.setId(randomId);
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
//        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
    }

    @Override
    public boolean authenticateUser(String username, String password) {
        User user = userRepository.findByUsername(username);
//        return user != null && passwordEncoder.matches(password, user.getPassword());
        return true;
    }

    @Override
    public User getUserById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.orElse(null);
    }
    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequest authenticationRequest) {
        try {
            // 通过 userService 从数据库中获取用户信息
            User user = userRepository.findByUsername(authenticationRequest.getUsername());

            if (user != null) {
                // 比对明文密码
                if (authenticationRequest.getPassword().equals(user.getPassword())) {
                    // 返回成功消息
                    return ResponseEntity.ok("Authentication successful");
                } else {
                    // 密码不匹配，返回失败消息
                    return ResponseEntity.status(401).body("Authentication failed: Password incorrect");
                }
            } else {
                // 用户不存在，返回失败消息
                return ResponseEntity.status(401).body("Authentication failed: User not found");
            }
        } catch (Exception e) {
            // 处理其他可能的异常
            return ResponseEntity.status(500).body("Internal server error");
        }
    }
    @Override
    public boolean isUsernameTaken(String username) {
        User existingUser = userRepository.findByUsername(username);
        return existingUser != null;
    }

    @Override
    public boolean isEmailRegistered(String email) {
        User existingUser = userRepository.findByEmail(email);
        return existingUser != null;
    }

}

