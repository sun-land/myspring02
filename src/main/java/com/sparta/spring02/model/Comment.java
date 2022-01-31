package com.sparta.spring02.model;

import com.sparta.spring02.dto.CommentRequestDto;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Comment {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long commentId;

    @Column(nullable = false)
    private Long postId;

//    @Column(nullable = false)
//    private String username;

    @Column(nullable = false)
    private String contents;

    public Comment(CommentRequestDto commentRequestDto,Long postId) {
        this.postId = postId;
        this.contents = commentRequestDto.getContents();
    }
}
