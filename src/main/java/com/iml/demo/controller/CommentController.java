package com.iml.demo.controller;

import com.iml.demo.model.Comment;
import com.iml.demo.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000") // 替换为您的前端应用的地址
@RequestMapping("/api/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping
    public ResponseEntity<String> createComment(@RequestBody Comment comment) {
        // 实现创建评论逻辑
        return ResponseEntity.ok("Comment created successfully");
    }
}
