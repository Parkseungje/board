package com.example.demo.api.user.controller;

import com.example.demo.api.user.entity.Users;
import com.example.demo.api.user.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/board")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/user/{email}") // 특정 유저 검색
    public Users getUser(@PathVariable String email){
        return userService.getUser(email);
    }

    @GetMapping("/hello")
    public String test(){
        return "Hello, world";
    }
}
