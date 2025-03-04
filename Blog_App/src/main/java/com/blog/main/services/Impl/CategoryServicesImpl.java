package com.blog.main.services.Impl;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.blog.main.dto.CategoryDto;
import com.blog.main.entity.Category;
import com.blog.main.exception.ResourceNotFoundException;
import com.blog.main.payload.GeneralResponse;
import com.blog.main.repository.CategoryRepo;
import com.blog.main.services.CategoryServices;

@Service
public class CategoryServicesImpl implements CategoryServices {

	@Autowired
	CategoryDto categoryDto;
	
	@Autowired
	CategoryRepo categoryRepo;
	
	@Autowired
	ModelMapper modelMapper;
	
	
	
	//save category
	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		return CategoryToDto(categoryRepo.save(DtoToCategory(categoryDto)));
	}

	//	update category
	@Override
	public CategoryDto updateCategory(Integer categoryId, CategoryDto categoryDto) {
		Category category = categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", "Category ID", categoryId));
		
		category.setCategoryTitle(categoryDto.getCategoryTitle());
		category.setCategoryDescription(categoryDto.getCategoryDescription());
		
		Category updatedCategory =  categoryRepo.save(category);
		
		return  CategoryToDto(updatedCategory);
	}

	@Override
	public CategoryDto getCategory(Integer categoryId) {
		Category category = categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "category ID", categoryId));
		
		CategoryDto categoryDto = CategoryToDto(category);
		return categoryDto;
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		Category category = categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "Category ID", categoryId));
		categoryRepo.delete(category);
		
	}

	@Override
	public GeneralResponse getCategories(int pageNumber, int pageSize) {
		
		Pageable p =  PageRequest.of(pageNumber, pageSize);
		
		Page<Category> pages = categoryRepo.findAll(p);
		
		List<Category> categories = pages.getContent();;
		List<CategoryDto> categoryDtos = categories.stream().map(category -> CategoryToDto(category)).collect(Collectors.toList());
		
		GeneralResponse response = new GeneralResponse();
		response.setContent(categoryDtos);
		response.setLastPage(pages.isLast());
		response.setPageNumber(pages.getNumber());
		response.setPageSize(pages.getSize());
		response.setTotalElements(pages.getTotalElements());
		response.setTotalPages(pages.getTotalPages());
		return response;
	}


	private Category DtoToCategory(CategoryDto categoryDto) {
		Category category = modelMapper.map(categoryDto, Category.class);
		return category;
	}
	

	private CategoryDto CategoryToDto(Category category) {
		CategoryDto categoryDto = modelMapper.map(category, CategoryDto.class);
		return categoryDto;
	}
	
}
