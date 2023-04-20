package com.app.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.app.DTO.Userdto;
import com.app.Entities.Property;
import com.app.Entities.User;
import com.app.exception.resourceNotFoundException;

public interface adminServiceInterface {

	List<Property> getAllPendingProperties();

	List<Property> getAllOnholdProperties();

	ResponseEntity<String> approveProperty(Long propid) throws resourceNotFoundException;

	ResponseEntity<User> registerAdmin(Userdto admin);

//	Admin getAdminByMail(String name) throws resourceNotFoundException;

	
}
