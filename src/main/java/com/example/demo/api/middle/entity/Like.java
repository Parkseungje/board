package com.example.demo.api.middle.entity;

import com.example.demo.api.post.entity.Post;
import com.example.demo.api.user.entity.User;
import com.example.demo.global.entity.BaseEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "likes")
public class Like extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long likeId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;
}
