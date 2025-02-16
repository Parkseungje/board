package com.example.demo.api.user.service;

import com.example.demo.api.login.dto.JoinRequest;
import com.example.demo.api.login.dto.LoginRequest;
import com.example.demo.api.user.entity.User;
import com.example.demo.api.user.entity.UserPrincipal;
import com.example.demo.api.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public Optional<User> getCurrentUser(){

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (!(principal instanceof UserPrincipal)) {
            log.error("SecurityContext에서 가져온 principal이 UserPrincipal이 아닙니다. 현재 타입: {}", principal.getClass().getName());
            log.error("principal 값 : {}", principal);
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal principal2 = (UserPrincipal) authentication.getPrincipal();
        return userRepository.findByEmail(principal2.getUsername());
    }

    @Override
    public Optional<User> getUser(String email) {
        return userRepository.findByEmail(email);
    }

    @Override // 기존 파라미터는 loginId
    public Optional<User> getLoginUserByLoginEmail(String loginEmail){
        Optional<User> user = userRepository.findByEmail(loginEmail);
        return user;
    }

    /**
     *  로그인 기능
     *  화면에서 LoginRequest(loginEmail, password)을 입력받아 loginEmail password가 일치하면 User return
     *  loginEmail이 존재하지 않거나 password가 일치하지 않으면 null return
     */

    public User login(LoginRequest req) {
        Optional<User> optionalUser = userRepository.findByEmail(req.getEmail());

        // loginId와 일치하는 User가 없으면 null return
        if(optionalUser.isEmpty()) {
            return null;
        }

        User user = optionalUser.get();

        // 찾아온 User의 password와 입력된 password가 다르면 null return
        if(!user.getPassword().equals(req.getPassword())) {
            return null;
        }

        return user;
    }

    /**
     * loginId 중복 체크
     * 회원가입 기능 구현 시 사용
     * 중복되면 true return
     */
    public boolean checkLoginIdDuplicate(String loginEmail) {
        return userRepository.existsByEmail(loginEmail);
    }

    /**
     * 회원가입 기능 1
     * 화면에서 JoinRequest(loginId, password, nickname)을 입력받아 User로 변환 후 저장
     * loginId, nickname 중복 체크는 Controller에서 진행 => 에러 메세지 출력을 위해
     */
    public void join(JoinRequest req) {
        userRepository.save(req.toEntity());
    }
}
