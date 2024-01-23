package com.iml.demo.serviceImpl;

import com.iml.demo.model.Post;
import com.iml.demo.repository.PostRepository;
import com.iml.demo.service.PostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public List<Post> getAllPosts() {
        return (List<Post>)postRepository.findAll();
    }

    @Override
    public Post getPostById(Long id) {
        Optional<Post> optionalPost = postRepository.findById(id);
        return optionalPost.orElse(null);
    }

    @Override
    public void createPost(Post post) {
        postRepository.save(post);
    }

    // 可以添加其他PostService接口中定义的方法的实现
    @Override
    public void updatePost(Long id, Post updatedPost) {
        Optional<Post> optionalPost = postRepository.findById(id);
        if (optionalPost.isPresent()) {
            Post existingPost = optionalPost.get();
            // Update fields of existingPost with values from updatedPost
            existingPost.setTitle(updatedPost.getTitle());
            existingPost.setContent(updatedPost.getContent());
            // You may add more fields to update depending on your Post entity
            postRepository.save(existingPost);
        }
        // Handle case where post with given id is not found (optional)
    }

    @Override
    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }
}

