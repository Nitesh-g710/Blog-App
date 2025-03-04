package com.blog.main;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.blog.main.dto.CategoryDto;
import com.blog.main.dto.PostDto;
import com.blog.main.entity.Comment;

@SpringBootApplication
//@ComponentScan(basePackages = "com.blog.main.services")
//@SpringBootApplication(scanBasePackages = "com.blog.main")
public class BlogAppApplication {

	public static void main(String[] args) {

			SpringApplication.run(BlogAppApplication.class, args);

	}

	
	//creating modelmapper so whenever IOC container trying to create object of modelmapper it will create new one everytime by refering this bean
	@Bean
	 ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
	@Bean
	 CategoryDto categoryDto() {
		return new CategoryDto();
	}
	
	@Bean
	 Comment comment() {
		return new Comment();
	}

	
}
