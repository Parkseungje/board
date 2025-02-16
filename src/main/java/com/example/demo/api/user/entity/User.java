package com.example.demo.api.user.entity;

import com.example.demo.api.middle.entity.Comment;
import com.example.demo.api.middle.entity.Like;
import com.example.demo.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String username;
    private String password;
    private String email;

    private UserRole role;

    @OneToMany(mappedBy = "user")
    private List<Like> like = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Comment> comment = new ArrayList<>();

}
