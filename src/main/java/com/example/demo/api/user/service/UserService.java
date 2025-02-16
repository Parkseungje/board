package com.example.demo.api.user.service;

import com.example.demo.api.login.dto.JoinRequest;
import com.example.demo.api.login.dto.LoginRequest;
import com.example.demo.api.user.entity.User;

import java.util.Optional;

public interface UserService {
    public Optional<User> getUser(String email);

    Optional<User> getLoginUserByLoginEmail(String loginEmail);

    User login(LoginRequest loginRequest);

    boolean checkLoginIdDuplicate(String email);

    void join(JoinRequest req);

    public Optional<User> getCurrentUser();
}
