package com.blog.main.exception;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.hibernate.validator.internal.properties.Field;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.blog.main.payload.ResponseStructure;

//this class will handle the exception 

@ControllerAdvice
public class GlobalExceptionHandler {
	

	// we can pass many meta data for multiple classes
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ResponseStructure> resourceNotFoundHandler(ResourceNotFoundException ex) {
		String message = ex.getMessage();
		ResponseStructure rs = new ResponseStructure(message, false);
		return new ResponseEntity<ResponseStructure>(rs, HttpStatus.NOT_FOUND);
	}


	// exception name is from postman, exception same as it was throwing in postman
	// so we handling that exception and giving the proper error details
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleMethodArgsNotValidException(MethodArgumentNotValidException ex) {
		Map<String, String> rs = new HashMap<>();

		// it is important to take all the messages in UserDto when exception occurs
		// thats we we using map to get message of particular variables
		// we take all binding results and then get all errors and fetching every field
		//1
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String field = ((FieldError) error).getField();
			String message = error.getDefaultMessage();
			rs.put(field, message);
			
			
		});
		//2
//		for (FieldError errors : ex.getBindingResult().getFieldErrors()) {
//            rs.put(errors.getField(), errors.getDefaultMessage());
//        }

		return new ResponseEntity<Map<String, String>>(rs, HttpStatus.BAD_REQUEST);
	}
	
	


}
