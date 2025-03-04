package com.blog.main.services;

import com.blog.main.dto.CommentDto;

public interface CommentServices {
	
	CommentDto createComment(CommentDto commentDto ,Integer PostId);
	
	void deleteComment(Integer commentId);
}
