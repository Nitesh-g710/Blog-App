package com.blog.main.services;

import java.util.List;
import com.blog.main.dto.UserDto;
import com.blog.main.payload.GeneralResponse;

public interface UserServices {
	
	UserDto createUser(UserDto user) ;
	
	UserDto updateUser(Integer userId, UserDto user) ;
	
	void deleteUser(Integer userId);
	
	UserDto getUserById(Integer userId);
	
	GeneralResponse getAllUsers(int pageNumber, int pageSize) ;
	
}
