package com.blog.main.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient.ResponseSpec;

import com.blog.main.payload.GeneralResponse;
import com.blog.main.payload.ResponseStructure;
import com.blog.main.dto.UserDto;
import com.blog.main.services.UserServices;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	private UserServices userServices;
	
	//POST - create user
	@PostMapping("/saveUser")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
		System.out.println("user controller");
		UserDto createdUserDto = userServices.createUser(userDto);
		return new ResponseEntity<UserDto>(createdUserDto, HttpStatus.CREATED);
	}
	
	
	//GET - get user
	@GetMapping("/{id}")
	public ResponseEntity<UserDto> getUser(@PathVariable Integer id) {
		
		return new ResponseEntity<UserDto>(userServices.getUserById(id), HttpStatus.OK) ;

	}
	
	//GET - get all user
	@GetMapping("/allUser")
	public ResponseEntity<?> getAllUsers(
			@RequestParam(value = "pageNumber", defaultValue = "0", required = false)Integer pageNumber, 
			@RequestParam(value = "pageSize", defaultValue = "4", required = false) Integer pageSize) {
		  return new ResponseEntity<GeneralResponse>(userServices.getAllUsers(pageNumber, pageSize), HttpStatus.OK);

	}
	
	
	
	//DELETE - delete user
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseStructure> deleteUser(@PathVariable Integer id) { 
		userServices.deleteUser(id);
		return new ResponseEntity<ResponseStructure>(new ResponseStructure("User Deleted Sucessfully!", true), HttpStatus.OK);

	}
	
	//UPDATE - update user
	@PutMapping("/{id}")
	public ResponseEntity<UserDto> updateUser(@PathVariable Integer id, @Valid @RequestBody UserDto user) {
		UserDto updatedUser =  userServices.updateUser(id, user);
		return new ResponseEntity<UserDto>(updatedUser, HttpStatus.OK);

	}
	
	
}
