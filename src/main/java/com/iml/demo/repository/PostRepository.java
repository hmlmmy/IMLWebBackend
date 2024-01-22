package com.iml.demo.repository;

import com.iml.demo.model.Post;
import com.iml.demo.service.PostService;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Long> {
}
