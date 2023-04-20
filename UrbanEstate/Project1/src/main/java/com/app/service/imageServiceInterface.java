package com.app.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.app.Entities.Image;
import com.app.exception.resourceNotFoundException;

public interface imageServiceInterface {

	String uploadImage(Long propid, MultipartFile imageFile) throws resourceNotFoundException, IOException;

	List<Image> getAllImagesForProperty(Long propId) throws resourceNotFoundException;
	
	

}
