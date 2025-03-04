package com.blog.main.services;

import java.util.List;

import com.blog.main.dto.CategoryDto;
import com.blog.main.payload.GeneralResponse;

public interface CategoryServices {
	
	CategoryDto createCategory(CategoryDto categoryDto);
	
	CategoryDto updateCategory(Integer categoryId, CategoryDto categoryDto);
	
	CategoryDto getCategory(Integer categoryId);
	
	void deleteCategory(Integer categoryId);
	
	GeneralResponse getCategories(int pageNumber, int pageSize);
	
}
