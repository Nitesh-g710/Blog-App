package com.blog.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.blog.main.entity.Post;
import java.util.List;

import com.blog.main.dto.PostDto;
import com.blog.main.entity.Category;
import com.blog.main.entity.User;



public interface PostRepo extends JpaRepository<Post, Integer>{
	
	List<Post> findByCategory(Category category);
	
	List<Post> findByUser(User user);
	
	@Query("select p from Post p where p.title LIKE LOWER(CONCAT('%', ?1, '%'))")
	List<Post> findByTitle(String title);
	
	// this is part of derived query methods
//	List<Post> findByTitleContaining(String keyword);
}	
