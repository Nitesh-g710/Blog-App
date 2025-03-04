package com.blog.main.services.Impl;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.blog.main.dto.UserDto;
import com.blog.main.entity.User;
import com.blog.main.exception.ResourceNotFoundException;
import com.blog.main.payload.GeneralResponse;
import com.blog.main.repository.UserRepo;
import com.blog.main.services.UserServices;

@Service
class UserServicesImpl implements UserServices {

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	//get data as userDto convert it into user oerform operation and 
	//convert user into userDto and return it
	
	
	@Override
	public UserDto createUser(UserDto user) {
		
		User user1 = dtoToUser(user);
		User saveUser = this.userRepo.save(user1);
		System.out.println("user service");
		return this.userToDto(saveUser);
		
	}

	@Override
	public UserDto updateUser(Integer userId, UserDto userDto) 	 {
		
		User user = userRepo.findById(userId)
		        .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

		user.setAbout(userDto.getAbout());
		user.setEmail(userDto.getEmail());
		user.setName(userDto.getName());
		user.setPassword(userDto.getPassword());
		return this.userToDto(userRepo.save(user));
		
//		User updateUser = userRepo.save(user);
//		UserDto userDto1 = userToDto(updateUser);
//		return userDto1;		
	}

	@Override
	public void deleteUser(Integer userId)  {
		User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "Id", userId) );
		this.userRepo.delete(user);
		
	}

	@Override
	public UserDto getUserById(Integer userId) {
		User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "Id", userId));
		
		return this.userToDto(user);
	}

	@Override
	public GeneralResponse getAllUsers(int pageNumber, int pageSize) {
		
		Pageable userPages = PageRequest.of(pageNumber, pageSize);
		
		Page<User> page = userRepo.findAll(userPages);
		
		List<User> users =  page.getContent();
		
		List<UserDto> userDtos = users.stream().map(user->this.userToDto(user)).collect(Collectors.toList());
		
		return new GeneralResponse(userDtos, page.getNumber(), page.getSize(), page.getTotalPages(), page.getTotalElements(), page.isLast());
	}
	
	private User dtoToUser(UserDto userDto) {
		//1
//		User user = new User();
//		user.setId(userDto.getId());
//		user.setName(userDto.getName());
//		user.setEmail(userDto.getEmail());
//		user.setPassword(userDto.getPassword());
//		user.setAbout(userDto.getAbout());
//		return user;
		
		
		//2
		User user = this.modelMapper.map(userDto, User.class);
		return user;
	}
	
	private UserDto userToDto(User user) {
		//1
//		UserDto userDto = new UserDto();
//		userDto.setId(user.getId());
//		userDto.setName(user.getName());
//		userDto.setEmail(user.getEmail());
//		userDto.setPassword(user.getPassword());
//		userDto.setAbout(user.getAbout());
//		
//		return userDto;
		
		
		//2
		UserDto userDto = this.modelMapper.map(user, UserDto.class);
		return userDto;

	}

}
