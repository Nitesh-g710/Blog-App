package com.blog.main.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
public class CategoryDto {

	private Integer categoryId;
	
	@NotBlank
	@Size(min = 4)
	private String categoryTitle;
	
	@Size(min = 10)
	private String categoryDescription;
	
	
	
}
