package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.Entities.Image;
import com.app.Entities.Property;
import com.app.Repository.ImageRepository;
import com.app.exception.resourceNotFoundException;
import com.app.service.imageServiceImpl;

@RestController
@RequestMapping("/images")
public class ImageController {

	@Autowired
	private imageServiceImpl imageServ;
	
	@GetMapping("/{propId}")
	public List<Image> getAllImagesForProperty(@PathVariable Long propId) throws resourceNotFoundException{
		
		return imageServ.getAllImagesForProperty(propId);
	}
	
}
