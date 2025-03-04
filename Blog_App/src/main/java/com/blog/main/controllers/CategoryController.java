package com.blog.main.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.main.dto.CategoryDto;
import com.blog.main.payload.GeneralResponse;
import com.blog.main.payload.ResponseStructure;
import com.blog.main.repository.CategoryRepo;
import com.blog.main.services.CategoryServices;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	
	@Autowired
	private CategoryServices categoryServices;
	
	//create 
	@PostMapping("/")
	public ResponseEntity<CategoryDto> saveCategory(@Valid @RequestBody CategoryDto categoryDto) {
		CategoryDto categoryDto2 = categoryServices.createCategory(categoryDto);
	
		return new ResponseEntity<CategoryDto>(categoryDto2, HttpStatus.CREATED);
	}
	
	
	//update
	@PutMapping("/{catId}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto, @PathVariable Integer catId) {
		// TODO Auto-generated method stub
		CategoryDto categoryDto2 = categoryServices.updateCategory(catId, categoryDto);
		return new ResponseEntity<CategoryDto>(categoryDto2, HttpStatus.OK);
	}	
	
	
	//get
	@GetMapping("/get")
	public ResponseEntity<CategoryDto> getMethodName(@RequestParam Integer categoryId ) {
		CategoryDto categoryDto = categoryServices.getCategory(categoryId);
		return new ResponseEntity<CategoryDto>(categoryDto, HttpStatus.OK);
	}
	
	//getAll
	@GetMapping("/getAll")
	public ResponseEntity<GeneralResponse> getCategories(
			@RequestParam(value = "pageNumber", defaultValue = "0", required = false)Integer pageNumber, 
			@RequestParam(value = "pageSize", defaultValue = "3", required = false)Integer pageSize) {
		GeneralResponse categoryDtos = categoryServices.getCategories(pageNumber, pageSize);
		return new ResponseEntity<GeneralResponse>(categoryDtos, HttpStatus.OK);
	}
	
	
	//delete
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ResponseStructure> deleteCategory(@PathVariable Integer id) {
		categoryServices.deleteCategory(id);
		ResponseStructure rs = new ResponseStructure();
		rs.setMessage("category deleted sucessfully !");
		rs.setSucess(true);
		return new ResponseEntity<ResponseStructure>(rs, HttpStatus.OK);
	}
}
