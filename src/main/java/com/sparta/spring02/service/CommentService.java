package com.sparta.spring02.service;

import com.sparta.spring02.dto.CommentRequestDto;
import com.sparta.spring02.model.Comment;
import com.sparta.spring02.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    // 해당 게시글의 댓글 조회하기
    public List<Comment> getAllComment(Long postId) {
        return commentRepository.findByPostId(postId);
    }

    // 해당 게시글에 댓글 저장하기
    public Comment createComment(CommentRequestDto commentRequestDto, Long postId) {
        Comment comment = new Comment(commentRequestDto,postId);
        commentRepository.save(comment);
        return comment;
    }

    // 댓글 삭제하기
    public Long deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
        return commentId;
    }
}
