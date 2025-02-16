package com.example.demo.api.post.controller;

import com.example.demo.api.post.dto.PostRequest;
import com.example.demo.api.post.entity.Post;
import com.example.demo.api.post.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@Slf4j
public class PostController {

    private final PostService postService;

    public PostController(PostService postService){
        this.postService = postService;
    }

    @GetMapping("/posts")
    public List<Post> getPosts() {
        List<Post> postList = postService.getPostsListAll();
        return postList;
    }

    @GetMapping("posts/{postId}")
    public Post getPost(@PathVariable Long postId) {
        return postService.getPost(postId).orElseThrow(()-> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));
    }

    @PostMapping("/post")
    public void savePost(@RequestBody PostRequest postRequest){
        postService.createPost(postRequest);
    }

    @DeleteMapping("/post/{postId}")
    public Long deletePost(@PathVariable Long postId){
        Long postNumber = postService.deletePost(postId);

        return postNumber;
    }

}

