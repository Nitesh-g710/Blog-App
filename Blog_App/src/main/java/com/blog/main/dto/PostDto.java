package com.blog.main.dto;

import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.blog.main.entity.Category;
import com.blog.main.entity.Comment;
import com.blog.main.entity.User;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
public class PostDto {
	
	private Integer postId;
	
	@NotBlank
	private String title;
	
	@NotBlank
	private String content;
	
	
	private String imageName;
	
	
	private CategoryDto category;
	
	private UserDto user;
	
	@CreationTimestamp
	private Date addedDate;
	
	@UpdateTimestamp
	private Date updatedDate;
	
	private List<Comment> comments ;
}
