package com.blog.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.main.entity.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
