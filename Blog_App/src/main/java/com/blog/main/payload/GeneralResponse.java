package com.blog.main.payload;

import java.util.List;

import com.blog.main.dto.PostDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//making this class so that we can pass response related to post for eg how many post, last page , first pg, content etc.,

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GeneralResponse {
	private List<?> content;
	private int pageNumber;
	private int pageSize;
	private int totalPages;
	private long totalElements;
	private boolean lastPage;
	
}
