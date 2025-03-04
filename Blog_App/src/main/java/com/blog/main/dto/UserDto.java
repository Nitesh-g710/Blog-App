package com.blog.main.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
public class UserDto {
	private Integer uId;
	
	@Size(min = 4,message = "Username must be min of 4 characters")
	@NotEmpty
	private String name;
	
	@Email(message = "Email address is not valid !!")
	private String email;
	
	@NotEmpty
	@Size(min = 3, max = 10, message = "password must be min 3 chars and max must be 10 chars")
//	can use pattern to check
//	@Pattern(regexp = )
	private String password;
	
	@NotNull
	private String about;
}
