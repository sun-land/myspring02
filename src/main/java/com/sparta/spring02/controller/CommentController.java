package com.sparta.spring02.controller;

import com.sparta.spring02.dto.CommentRequestDto;
import com.sparta.spring02.model.Comment;
import com.sparta.spring02.model.Post;
import com.sparta.spring02.service.CommentService;
import com.sparta.spring02.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentController {
    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    // 해당 게시글의 댓글 조회하기
    @PostMapping("/posts/{postId}/comments")
    public Comment createComment(@RequestBody CommentRequestDto commentRequestDto, @PathVariable Long postId) {
        Comment comment = commentService.createComment(commentRequestDto,postId);
        return comment;
    }

    // 해당 게시글에 댓글 저장하기
    @GetMapping("/posts/{postId}/comments")
    public List<Comment> getAllComment(@PathVariable Long postId) {
        return commentService.getAllComment(postId);
    }

    // 댓글 삭제하기
    @DeleteMapping("/comments/{commentId}")
    public Long deleteComment(@PathVariable Long commentId) {
        return commentService.deleteComment(commentId);
    }
}
