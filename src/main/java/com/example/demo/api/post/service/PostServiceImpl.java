package com.example.demo.api.post.service;

import com.example.demo.api.post.entity.Post;
import com.example.demo.api.post.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService{

    private final PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository){
        this.postRepository=postRepository;
    }

    public List<Post> getPostsListAll(){
        return postRepository.findAll();
    }

    public Optional<Post> getPost(Long postId) {
        return postRepository.findById(postId);
    }
}
