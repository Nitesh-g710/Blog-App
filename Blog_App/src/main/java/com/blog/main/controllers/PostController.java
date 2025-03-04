 package com.blog.main.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.blog.main.constants.AppContants;
import com.blog.main.dto.PostDto;
import com.blog.main.payload.GeneralResponse;
import com.blog.main.repository.PostRepo;
import com.blog.main.services.FileServices;
import com.blog.main.services.PostServices;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/post")
public class PostController {

	@Autowired
	PostServices postServices;
	
	@Autowired
	FileServices fileServices;
	
	@Value("${project.image}")
	private String path ; 

	
	
	//method to serve files
		@GetMapping(value="/images/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
		public void downloadImage(@PathVariable("imageName") String imageName, HttpServletResponse response) throws IOException {
			
			InputStream resource = this.fileServices.getResource(path, imageName);
			
			//set response type
			response.setContentType(MediaType.IMAGE_JPEG_VALUE);
			
			StreamUtils.copy(resource, response.getOutputStream());	
		}
	
	//post image upload
	@PostMapping("/image/upload/{postId}")
	public ResponseEntity<PostDto> uploadImage(@RequestParam("image") MultipartFile image, @PathVariable Integer postId) throws IOException {
		String fileName = this.fileServices.uploadImage(path, image);
		PostDto postDto = postServices.getPostById(postId);
		postDto.setImageName(fileName);
		PostDto updatedPost = postServices.updatePost(postId, postDto);
		return new ResponseEntity<PostDto>(updatedPost, HttpStatus.OK);  
		
	}
	
	// create
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable Integer userId,
			@PathVariable Integer categoryId) {
		PostDto dto = postServices.createPost(postDto, userId, categoryId);
		return new ResponseEntity<PostDto>(dto, HttpStatus.CREATED);
	}

	//delete by
	@DeleteMapping("/post/{postId}")
	public ResponseEntity<Boolean> deletePostById(@PathVariable Integer postId) {
		Boolean bool = postServices.deletePost(postId);
		return new ResponseEntity<Boolean>(bool, HttpStatus.OK);
	}
	
	//get All
	@GetMapping("/allPost")
	public ResponseEntity<GeneralResponse> getAllPosts(
			@RequestParam(value = "pageNumber", defaultValue = AppContants.PAGE_NUMBER, required = false)Integer pageNumber, 
			@RequestParam(value = "pageSize", defaultValue = AppContants.PAGE_SIZE, required = false)Integer pageSize ,
			@RequestParam(value = "sortBy", defaultValue = AppContants.SORT_BY, required = false)String sortBy,
			@RequestParam(value="sortDir", defaultValue = AppContants.SORT_DIR, required = false)String sortDir) {
		GeneralResponse dtos = postServices.getAllPost( pageNumber, pageSize, sortBy , sortDir);
		return new ResponseEntity<GeneralResponse>(dtos, HttpStatus.OK);

	}
		
	//get by id
	@GetMapping("/{pid}")
	public ResponseEntity<PostDto> getPostById(@PathVariable Integer pid) {
		PostDto postDto = postServices.getPostById(pid);
		return new ResponseEntity<PostDto>(postDto, HttpStatus.OK);
	}
	
	
	// get by user
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable Integer userId) {
		List<PostDto> post = postServices.getPostByUser(userId);
		return new ResponseEntity<List<PostDto>>(post, HttpStatus.OK);
	}
	
	
	// get by category
	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable Integer categoryId) {
		List<PostDto> posts = postServices.getPostByCategory(categoryId);
		return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);
	}
	
	//update post
	@PutMapping("/{postId}")
	public ResponseEntity<PostDto> updatePostById(@PathVariable Integer postId, @RequestBody PostDto post) {
		PostDto updatedPost =  postServices.updatePost(postId, post);
		return new ResponseEntity<PostDto>(updatedPost, HttpStatus.OK);
		
	}
	
	// search post
	@GetMapping("/posts/search/{keyword}")
	public ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable String keyword) {
		List<PostDto> posts = postServices.searchPost(keyword);
		return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);
		
	}
}
