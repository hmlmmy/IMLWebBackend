package com.iml.demo.service;

import com.iml.demo.model.Post;

import java.util.List;

public interface PostService {
    List<Post> getAllPosts();

    Post getPostById(Long id);

    void createPost(Post post);

    // 其他方法...
}
