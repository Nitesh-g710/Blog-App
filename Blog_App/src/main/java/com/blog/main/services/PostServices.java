package com.blog.main.services;

import java.util.List;

import com.blog.main.dto.PostDto;
import com.blog.main.entity.Post;
import com.blog.main.payload.GeneralResponse;

public interface PostServices {
	
	//create
	PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);
	
	//delete
	boolean deletePost(Integer id);
	
	//get
	PostDto getPostById(Integer id);
	
	//getAll
	GeneralResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy,String sortDir);
	
	//get all by category id
	List<PostDto> getPostByCategory(Integer categoryId);
	
	//get all by user id
	List<PostDto> getPostByUser(Integer userId);
	
	PostDto updatePost(Integer postId, PostDto post);
	
	List<PostDto> searchPost(String keyword);
}
