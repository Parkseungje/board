package com.example.demo.api.user.controller;

import com.example.demo.api.user.entity.User;
import com.example.demo.api.user.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/user/{email}") // 특정 유저 검색
    public Optional<User> getUser(@PathVariable String email){
        return userService.getUser(email);
    }

    @GetMapping("/hello")
    public String test(){
        return "Hello, world";
    }
}
