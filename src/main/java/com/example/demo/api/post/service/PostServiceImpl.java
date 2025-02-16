package com.example.demo.api.post.service;

import com.example.demo.api.post.dto.PostRequest;
import com.example.demo.api.post.entity.Post;
import com.example.demo.api.post.repository.PostRepository;
import com.example.demo.api.user.entity.User;
import com.example.demo.api.user.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService{

    private final PostRepository postRepository;
    private final UserService userService;

    public List<Post> getPostsListAll(){
        return postRepository.findAll();
    }

    public Optional<Post> getPost(Long postId) {
        return postRepository.findById(postId);
    }

    @Transactional
    public void createPost(PostRequest postRequest) {
        Post.PostBuilder builder = getPostBuilder(postRequest);
        postRepository.save(builder.build());
    }

    @Transactional
    public Long deletePostById(Long postId){
        postRepository.deleteById(postId);
        return postId;
    }

    @Transactional
    public void updatePostById(Long postId, PostRequest postRequest){
        Post postEntity = postRepository.findById(postId).get();

        Post.PostBuilder builder = getPostBuilder(postRequest);
        builder.postId(postEntity.getPostId());

        postRepository.save(builder.build());
    }

    private Post.PostBuilder getPostBuilder(PostRequest postRequest) {
        Post.PostBuilder builder = Post.builder();

        builder.title(postRequest.getTitle());
        builder.content(postRequest.getContent());
        builder.user(userService.getCurrentUser().get());
        return builder;
    }
}
