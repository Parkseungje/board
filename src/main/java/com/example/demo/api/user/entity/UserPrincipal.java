package com.example.demo.api.user.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@AllArgsConstructor
public class UserPrincipal implements UserDetails, Serializable {

    private static final long serialVersionUID = 174726374856727L;

    private String password;	// 비밀번호
    private String email;	//이메일
    private Collection<GrantedAuthority> authorities;	//권한 목록


    /**
     * 해당 유저의 권한 목록
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    /**
     * 비밀번호
     */
    @Override
    public String getPassword() {
        return password;
    }


    /**
     * PK값
     * 계정의 고유한 값을 리턴
     * ( ex : DB PK값, 중복이 없는 이메일 값 )
     */
    @Override
    public String getUsername() {
        return email;
    }

    /**
     * 계정 만료 여부
     * true : 만료 안됨
     * false : 만료
     * @return
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }


    /**
     * 비밀번호 만료 여부
     * true : 만료 안됨
     * false : 만료
     * @return
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    public static UserPrincipal create(User user) {
        List<GrantedAuthority> authorities = Collections.singletonList(
                new SimpleGrantedAuthority(user.getRole().name())
        );

        return new UserPrincipal(
                user.getPassword(),
                user.getEmail(),
                authorities
        );
    }
}
