package com.blog.main.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.main.dto.CommentDto;
import com.blog.main.payload.GeneralResponse;
import com.blog.main.payload.ResponseStructure;
import com.blog.main.services.CommentServices;

@RestController
@RequestMapping("/api")
public class CommentController {
	
	@Autowired
	CommentServices commentServices;
	
	@PostMapping("/post/{postId}/comments")
	public ResponseEntity<CommentDto> createComments(@RequestBody CommentDto commentDto, @PathVariable Integer postId) {
		
		CommentDto comment = commentServices.createComment(commentDto, postId);
		System.out.println("comment controller");
		return new ResponseEntity<CommentDto>(comment, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/comments/{commentId}")
	public ResponseEntity<ResponseStructure> deleteComment(@PathVariable Integer commentId) {
		
		commentServices.deleteComment(commentId);
		
		return new ResponseEntity<ResponseStructure>(new ResponseStructure("Comment Deleted Sucessfully!!", true), HttpStatus.OK);
		

	}
}
