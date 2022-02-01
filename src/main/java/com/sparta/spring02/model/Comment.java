package com.sparta.spring02.model;

import com.sparta.spring02.dto.CommentRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Comment {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long commentId;

    @Column(nullable = false)
    private Long postId;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String contents;

    // 생성자
    public Comment(CommentRequestDto commentRequestDto,Long postId) {
        this.postId = postId;
        this.contents = commentRequestDto.getContents();
        this.username = commentRequestDto.getUsername();
    }

    // 수정하기
    public void update(CommentRequestDto commentRequestDto) {
        this.contents = commentRequestDto.getContents();
    }
}
