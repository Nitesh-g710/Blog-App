package com.blog.main.entity;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;



@Entity
@Data
@Table(name="comments")
public class Comment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer commentId;
	
	private String contents;
	
	//comment we will fetch with post we will not include in dto
	@ManyToOne
	private Post post;
	
//	@ManyToOne
//	@JoinColumn(name = "uId")
//	private User user;
	
}
