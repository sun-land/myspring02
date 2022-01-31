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

//    @Column(nullable = false)
//    private String username;

    @Column(nullable = false)
    private String contents;

    // postId까지 받아오는 생성자
    public Comment(CommentRequestDto commentRequestDto,Long postId) {
        this.postId = postId;
        this.contents = commentRequestDto.getContents();
    }
    // 임시로 만든 postId없는 생성자
//    public Comment(CommentRequestDto commentRequestDto) {
//        this.contents = commentRequestDto.getContents();
//    }
}
