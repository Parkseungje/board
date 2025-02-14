package com.example.demo.api.user.service;

import com.example.demo.api.user.entity.User;
import com.example.demo.api.user.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public User getUser(String email) {
        return userRepository.findByEmail(email);
    }
}
