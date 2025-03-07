package com.blog.main.services.Impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.blog.main.services.FileServices;



@Service
public class FileServicesImpl implements FileServices {

	@Override
	public String uploadImage(String path, MultipartFile file) throws IOException {
		// file name
		String name = file.getOriginalFilename();

		// it will generate random id
		String randomID = UUID.randomUUID().toString();

		String newFileName = randomID.concat(name.substring(name.indexOf('.')));

		// full path ( File.separator= '/')
		String filePath = path + File.separator + newFileName;

		// create a folder if not created
		File f = new File(path);
		if (!f.exists()) {
			f.mkdir();
		}

		// copy file
		Files.copy(file.getInputStream(), Paths.get(filePath));

		return newFileName;

	}

	@Override
	public InputStream getResource(String path, String fileName) throws FileNotFoundException {
			
		String fullPath =path+File.separator+fileName;
		InputStream is = new  FileInputStream(fullPath);
		//db logic to return inputstream
		return is;
		
	}

}
