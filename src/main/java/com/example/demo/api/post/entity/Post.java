package com.example.demo.api.post.entity;

import com.example.demo.api.middle.entity.Comment;
import com.example.demo.api.middle.entity.Like;
import com.example.demo.api.user.entity.User;
import com.example.demo.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Table(name = "posts")
public class Post extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    private String title;
    private String content;
    private int likeCount = 0;
    protected LocalDateTime updatedAt;


    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "post")
    private List<Like> like = new ArrayList<>();

    @OneToMany(mappedBy = "post")
    private List<Comment> comment = new ArrayList<>();

    @Override
    public String toString() {
        return "Posts{" +
                "postId=" + postId +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", likeCount=" + likeCount +
                ", user=" + user +
                '}';
    }
}
