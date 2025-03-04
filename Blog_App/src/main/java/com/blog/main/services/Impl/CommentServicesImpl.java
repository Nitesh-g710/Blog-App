package com.blog.main.services.Impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.main.dto.CommentDto;
import com.blog.main.entity.Comment;
import com.blog.main.entity.Post;
import com.blog.main.exception.ResourceNotFoundException;
import com.blog.main.repository.CommentRepo;
import com.blog.main.repository.PostRepo;
import com.blog.main.services.CommentServices;

@Service
public class CommentServicesImpl implements CommentServices{

	@Autowired
	PostRepo postRepo;
	
	@Autowired
	CommentRepo commentRepo;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Override
	public CommentDto createComment(CommentDto commentDto, Integer PostId) {
		Post post = postRepo.findById(PostId).orElseThrow(()-> new ResourceNotFoundException("Post", "post id", PostId));
		
		Comment comment = this.modelMapper.map(commentDto, Comment.class);
		
		comment.setPost(post);
		
		Comment savedComment = this.commentRepo.save(comment);
		System.out.println("comment services");
		return this.modelMapper.map(savedComment, CommentDto.class); 
	}

	@Override
	public void deleteComment(Integer commentId) {
		Comment comment = commentRepo.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comment", "comment id", commentId));
		commentRepo.deleteById(commentId);
		
	}

}
