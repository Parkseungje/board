package com.example.demo.api.user.service;

import com.example.demo.api.user.entity.User;
import com.example.demo.api.user.entity.UserPrincipal;
import com.example.demo.api.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserPrincipalService {

    private final UserRepository userRepository;

    public UserDetails loadUserByUsername(String email) {
            User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found with email: " + email));

        UserPrincipal userPrincipal = UserPrincipal.create(user);

        if (!(userPrincipal instanceof UserDetails)) {
            throw new ClassCastException("UserPrincipal이 UserDetails 타입이 아닙니다.");
        }

        return userPrincipal;
    }
}
