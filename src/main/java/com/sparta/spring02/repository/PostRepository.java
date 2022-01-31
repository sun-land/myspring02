package com.sparta.spring02.repository;

import com.sparta.spring02.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Long>  {

}
