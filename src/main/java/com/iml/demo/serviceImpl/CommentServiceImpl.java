package com.iml.demo.serviceImpl;

import com.iml.demo.model.Comment;
import com.iml.demo.repository.CommentRepository;
import com.iml.demo.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Override
    public void createComment(Comment comment) {
        commentRepository.save(comment);
    }

    @Override
    public List<Comment> getAllComments() {
        return (List<Comment>)commentRepository.findAll();
    }
    // 其他方法的实现...
}

