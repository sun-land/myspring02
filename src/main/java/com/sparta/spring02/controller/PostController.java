package com.sparta.spring02.controller;

import com.sparta.spring02.dto.PostRequestDto;
import com.sparta.spring02.model.Post;
import com.sparta.spring02.repository.PostRepository;
import com.sparta.spring02.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {
    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    // 모든 게시글 조회하기
    @GetMapping("/posts")
    public List<Post> getAllPost() {
        return postService.allPosts();
    }

    // 게시글 작성하기
    @PostMapping("/posts")
    public Post createPost(@RequestBody PostRequestDto postRequestDto) {
        return postService.createPost(postRequestDto);
    }

    // 게시글 상세보기
    @GetMapping("/posts/{postId}")
    public Post getDetailPost(@PathVariable Long postId) {
        Post post = postService.getDetailPost(postId);
        return post;
    }


    // 게시글 삭제하기
    @DeleteMapping("/posts/{postId}")
    public Long deletePost(@PathVariable Long postId) {
        return postService.deletePost(postId);
    }
}
