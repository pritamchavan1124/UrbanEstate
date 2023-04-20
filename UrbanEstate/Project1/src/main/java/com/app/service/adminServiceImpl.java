package com.app.service;

import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.DTO.Userdto;

import com.app.Entities.Property;
import com.app.Entities.Role;
import com.app.Entities.Status;
import com.app.Entities.User;
import com.app.Repository.UserRepositiory;
import com.app.Repository.propertyRepository;
import com.app.exception.resourceNotFoundException;

@Service
@Transactional
public class adminServiceImpl implements adminServiceInterface {

	@Autowired
	private UserRepositiory adminRepo;
	@Autowired
	private propertyRepository propRepo;
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private PasswordEncoder encoder;
	@Override
	public List<Property> getAllPendingProperties() {
		
		return propRepo.findByStatus(Status.PENDING);
	}
	
	@Override
	public List<Property> getAllOnholdProperties() {
		
		return propRepo.findByStatus(Status.ONHOLD);
	}

	@Override
	public ResponseEntity<String> approveProperty(Long propid) throws resourceNotFoundException {
		
		Property property = propRepo.findById(propid).orElseThrow(()->new resourceNotFoundException("Property's not available"));
		property.setStatus(Status.APPROVED);
		return new ResponseEntity<String>("Property has been approved", HttpStatus.OK);
		
	}
	
	@Override
	public ResponseEntity<User> registerAdmin(Userdto admin) {
			User user = mapper.map(admin, User.class);
		user.setUserRole(Role.ROLE_ADMIN);
		user.setPassword(encoder.encode(admin.getPassword()));
//		System.out.println(user);
		adminRepo.save(user);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

//	@Override
//	public Admin getAdminByMail(String name) throws resourceNotFoundException {
//	
//		return adminRepo.findByEmail(name).orElseThrow(()->new resourceNotFoundException("Invalid Email"));
//	}

	
}
