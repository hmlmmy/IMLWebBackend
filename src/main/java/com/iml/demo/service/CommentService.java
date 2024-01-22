package com.iml.demo.service;

import com.iml.demo.model.Comment;

import java.util.List;

public interface CommentService {
    void createComment(Comment comment);
    List<Comment> getAllComments();
}
