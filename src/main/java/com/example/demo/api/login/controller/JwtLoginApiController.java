package com.example.demo.api.login.controller;

import com.example.demo.api.login.dto.JoinRequest;
import com.example.demo.api.login.dto.LoginRequest;
import com.example.demo.api.user.entity.User;
import com.example.demo.api.user.service.UserService;
import com.example.demo.global.security.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/jwt-login")
@Slf4j
public class JwtLoginApiController {

    private final UserService userService;
    private final JwtTokenUtil jwtTokenUtil;

    @PostMapping("/join")
    public String join(@RequestBody JoinRequest joinRequest) {

        // loginId 중복 체크
        if(userService.checkLoginIdDuplicate(joinRequest.getEmail())) {
            return "로그인 이메일이 중복됩니다.";
        }

        // password와 passwordCheck가 같은지 체크
        if(!joinRequest.getPassword().equals(joinRequest.getPasswordCheck())) {
            return"바밀번호가 일치하지 않습니다.";
        }

        userService.join(joinRequest);
        return "회원가입 성공";
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest) {

        User user = userService.login(loginRequest);

        // 로그인 아이디나 비밀번호가 틀린 경우 global error return
        if(user == null) {
            return"로그인 아이디 또는 비밀번호가 틀렸습니다.";
        }

        // 로그인 성공 => Jwt Token 발급

        String secretKey = "my-secret-key-123123";
        long expireTimeMs = 1000 * 60 * 60;     // Token 유효 시간 = 60분

        String jwtToken = jwtTokenUtil.createToken(user.getEmail(), expireTimeMs);

        return jwtToken;
    }

    @GetMapping("/info")
    public String userInfo(Authentication auth) {
        Optional<User> loginUser = userService.getLoginUserByLoginEmail(auth.getName());

        return String.format("loginEmail : %s\nrole : %s",
                loginUser.get().getEmail(), loginUser.get().getRole().name());
    }

    @GetMapping("/admin")
    public String adminPage() {
        return "관리자 페이지 접근 성공";
    }
}
