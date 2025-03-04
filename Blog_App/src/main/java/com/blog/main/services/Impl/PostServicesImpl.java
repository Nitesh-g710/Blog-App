package com.blog.main.services.Impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.hibernate.Hibernate;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.blog.main.dto.PostDto;
import com.blog.main.entity.Category;
import com.blog.main.entity.Comment;
import com.blog.main.entity.Post;
import com.blog.main.entity.User;
import com.blog.main.exception.ResourceNotFoundException;
import com.blog.main.payload.GeneralResponse;
import com.blog.main.repository.CategoryRepo;
import com.blog.main.repository.PostRepo;
import com.blog.main.repository.UserRepo;
import com.blog.main.services.PostServices;

import ch.qos.logback.classic.pattern.Util;
import ch.qos.logback.core.joran.util.beans.BeanUtil;
import jakarta.transaction.Transactional;

@Service
public class PostServicesImpl implements PostServices{

	@Autowired
	PostRepo postRepo;
	
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	CategoryRepo categoryRepo;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	Comment comment;
	
	@Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
		User user = userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "User Id", userId));
		
		Category category = categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "Category Id", categoryId));
		
		
		Post post = DtoToPost(postDto);
		post.setImageName("hello.png");
		post.setCategory(category);
		post.setUser(user);
		return PostToDto(postRepo.save(post));
	}

	@Override
	public boolean deletePost(Integer id) {
		Post post = postRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post", "Post Id", id));
		postRepo.deleteById(id);
		return true;
	}

	
//	@Transactional
	@Override
	public PostDto getPostById(Integer id) {
		Post post = postRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post", "Post Id", id));
		// Force Hibernate to initialize comments before mapping to DTO
//	    post.setComments(new HashSet<>(post.getComments())); 
		
		// Force Hibernate to load the comments collection
//	    Hibernate.initialize(post.getComments());
	    
		return this.modelMapper.map(post, PostDto.class);
	}

	
	@Override 
	public GeneralResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
		
		
		// get data by page number and 1 page content 5 no. of data 
		// Sort.by can sort attribute
		//Sort.by(sortBy).ascending()
		//Sort.by(sortBy).descending()
		
		Sort sort = sortDir.equalsIgnoreCase("asc")? Sort.by(sortBy).ascending(): Sort.by(sortBy).descending();
		
		
		Pageable pages =PageRequest.of(pageNumber, pageSize, sort);  // getting the peagable object
		
		// fetches posts for page 1 with 5 posts per page.
		Page<Post> pagePost = postRepo.findAll(pages);
		
		// extracts only the list of Post objects from the fetched page.
		List<Post> allPost =  pagePost.getContent();
		
		
		// List<Post> postDtos = postRepo.findAll();
		List<PostDto> postDto = allPost.stream().map(post -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		// List<PostDto> postDto=allPost.stream().map(post -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		System.out.println("all posts DTOs : "+postDto);
		
		
		GeneralResponse postResponse = new GeneralResponse();
		postResponse.setContent(postDto);
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setTotalPages(pagePost.getTotalPages());
		postResponse.setTotalElements(pagePost.getTotalElements());
		postResponse.setLastPage(pagePost.isLast());
		return postResponse;
	}
	
	
	@Override
	public List<PostDto> getPostByCategory(Integer categoryId) {
		Category category = categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "Category Id", categoryId));
		List<Post> posts=  this.postRepo.findByCategory(category);
		return posts.stream().map((post) -> this.modelMapper.map( post, PostDto.class)).collect(Collectors.toList());
		 
	}

	@Override
	public List<PostDto> getPostByUser(Integer userId) {
		User user= userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "User Id", userId));
		List<Post> posts = postRepo.findByUser(user);
		return posts.stream().map(post -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
	}
	
	
	@Override
	public PostDto updatePost(Integer postId, PostDto post) {
		Post post1 = postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "Post Id", postId));
		post1.setTitle(post.getTitle());
		post1.setContent(post.getContent());
		post1.setImageName(post.getImageName());
		postRepo.save(post1);
		return PostToDto(post1); 
	}
	
	///search
	
	@Override
	public List<PostDto> searchPost(String keyword) {
		List<Post> posts = postRepo.findByTitle(keyword);
		List<PostDto> postDtos = posts.stream().map(post -> modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDtos;

	}

	private Post DtoToPost(PostDto postDto) {
		Post post = modelMapper.map(postDto, Post.class);
		return post;
	}
	
	private PostDto PostToDto(Post post) {
		PostDto postDto = modelMapper.map(post, PostDto.class);
		return postDto;
	}

	
	
}
