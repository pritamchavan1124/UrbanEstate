package com.app.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.app.DTO.propertyDTO;
import com.app.Entities.Property;
import com.app.exception.resourceNotFoundException;

public interface propertyServiceInterface {

	ResponseEntity<String> addNewProperty(propertyDTO property);
	 List<Property> getAllProperties();
	ResponseEntity<String> deleteProperty(Long propid);
	ResponseEntity<String> updateProperty(propertyDTO property) throws resourceNotFoundException;
	List<Property> getAllPropertiesInCity(String city);
	List<Property> getAllPropertiesByType(String type);
	List<Property> getAllPropertiesByPropertyFor(String propfor);
	Property getPropertyById(Long propId);
	List<Property> getAllApprovedProperties();
	
}
