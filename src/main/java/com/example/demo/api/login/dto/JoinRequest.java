package com.example.demo.api.login.dto;

import com.example.demo.api.user.entity.User;
import com.example.demo.api.user.entity.UserRole;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class JoinRequest {

    @NotBlank(message = "로그인 아이디가 비어있습니다.")
    private String email;

    @NotBlank(message = "비밀번호가 비어있습니다.")
    private String password;
    private String passwordCheck;

    @NotBlank(message = "닉네임이 비어있습니다.")
    private String username;

    // 비밀번호 암호화 X
    public User toEntity() {
        return User.builder()
                .email(this.email)
                .password(this.password)
                .username(this.username)
                .role(UserRole.USER)
                .build();
    }

    // 비밀번호 암호화
    public User toEntity(String encodedPassword) {
        return User.builder()
                .email(this.email)
                .password(encodedPassword)
                .role(UserRole.USER)
                .build();
    }
}
