package com.careerpulse.jobportal.controller;

import com.careerpulse.jobportal.model.User;
import com.careerpulse.jobportal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @PostMapping
    public User registerUser(@RequestBody User user) {
        return userRepository.save(user);
    }
}