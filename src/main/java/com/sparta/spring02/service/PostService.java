package com.sparta.spring02.service;

import com.sparta.spring02.dto.PostRequestDto;
import com.sparta.spring02.model.Post;
import com.sparta.spring02.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    // 모든 게시글 조회하기
    public List<Post> allPosts() {
        return postRepository.findAllByOrderByCreatedAtDesc();
    }

    // 게시글 작성하기
    public Post createPost(PostRequestDto postRequestDto) {
        Post post = new Post(postRequestDto);
        postRepository.save(post);
        return post;
    }

    // 게시글 상세보기
    public Post getDetailPost(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(()->new IllegalArgumentException("찾는 게시글이 없습니다."));
        return post;
    }

    // 게시글 삭제하기
    public Long deletePost(Long postId) {
        postRepository.deleteById(postId);
        return postId;
    }


}
