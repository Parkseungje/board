package com.example.demo.api.post.service;

import com.example.demo.api.post.dto.PostRequest;
import com.example.demo.api.post.entity.Post;

import java.util.List;
import java.util.Optional;

public interface PostService {

    List<Post> getPostsListAll();

    Optional<Post> getPost(Long postId);

    void createPost(PostRequest post);

    Long deletePostById(Long postId);

    void updatePostById(Long postId, PostRequest postRequest);
}
