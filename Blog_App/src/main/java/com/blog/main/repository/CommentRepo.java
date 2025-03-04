package com.blog.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blog.main.entity.Comment;

@Repository
public interface CommentRepo extends JpaRepository<Comment, Integer>{

}
