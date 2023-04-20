package com.app.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import com.app.Entities.Image;
import com.app.Entities.Property;
import com.app.Repository.ImageRepository;
import com.app.Repository.propertyRepository;
import com.app.exception.resourceNotFoundException;

@Service
@Transactional
public class imageServiceImpl implements imageServiceInterface {
	
	@Autowired
	private propertyRepository propRepo;

	@Autowired
	private ImageRepository imageRepo;
	
	@Value("${content.upload.folder}")
	private String folderName;
	
	@PostConstruct
	public void myInit() {
		System.out.println("in myInit " + folderName);
		// chk of folder exists --o.w create one!
		File path = new File(folderName);
		if (!path.exists()) {
			path.mkdirs();
		} else
			System.out.println("folder alrdy exists....");
	}
	@Override
	public String uploadImage(Long propid, MultipartFile imageFile) throws resourceNotFoundException, IOException {
		Property property = propRepo.findById(propid).orElseThrow(()->new resourceNotFoundException("Property's not available"));
		String targetPath=folderName+File.separator+imageFile.getOriginalFilename();
		
		System.out.println(targetPath);
		//copy image file contents to the specified path
		Files.copy(imageFile.getInputStream(), Paths.get(targetPath), StandardCopyOption.REPLACE_EXISTING);
		property.addImageToProperty(targetPath);
		
		//=> success
		//save image path in DB
		return "Image uploaded successfully";
	}
	@Override
	public List<Image> getAllImagesForProperty(Long propId) throws resourceNotFoundException {
		
		Property property=propRepo.findById(propId).orElseThrow(()->new resourceNotFoundException("Propert's not listed"));
		return imageRepo.findByProperty(property);
	}
	
	
	 

}
