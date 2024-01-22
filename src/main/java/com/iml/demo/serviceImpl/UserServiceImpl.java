package com.iml.demo.serviceImpl;

import com.iml.demo.model.User;
import com.iml.demo.repository.UserRepository;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.iml.demo.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public void registerUser(String username, String password) {
        User user = new User();
        user.setUsername(username);
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
    public User saveUser(User user) {
        return userRepository.save(user);
    }
}

