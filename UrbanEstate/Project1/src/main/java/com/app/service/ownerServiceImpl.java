package com.app.service;

import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.DTO.OwnerDTO;
import com.app.DTO.propertyDTO;
import com.app.Entities.Owner;
import com.app.Entities.Property;
import com.app.Entities.Role;
import com.app.Repository.ownerRepository;
import com.app.Repository.propertyRepository;
import com.app.exception.resourceNotFoundException;

@Service
@Transactional
public class ownerServiceImpl implements ownerServiceInterface {

	@Autowired
	private ownerRepository ownerRepo;
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private propertyRepository propRepo;
	@Autowired
	private PasswordEncoder encoder;
	
	@Override
	public ResponseEntity<Owner> registerOwner(OwnerDTO owner) {
		
		Owner owner2 = mapper.map(owner, Owner.class);
		owner2.setPassword(encoder.encode(owner.getPassword()));
		owner2.setUserRole(Role.ROLE_OWNER);
		ownerRepo.save(owner2);
		return new ResponseEntity<Owner>(owner2, HttpStatus.OK);
	}
	
	@Override
	public Property addPropertyToOwner(propertyDTO property, Long ownerid) throws resourceNotFoundException {

		Owner owner = ownerRepo.findById(ownerid).orElseThrow(()->new resourceNotFoundException("Owner's not registered"));
		Property property2 = mapper.map(property, Property.class);
		owner.addProperty(property2);
		property2.setOwner(owner);
		return property2;

	}
	@Override
	public Property deletePropertyFromOwner(Long propertyid, Long ownerid) throws resourceNotFoundException {
		
			Owner owner = ownerRepo.findById(ownerid).orElseThrow(()->new resourceNotFoundException("Owner's not registered"));
			Property property = propRepo.findById(propertyid).orElseThrow(()->new resourceNotFoundException("Propert's not available"));
			owner.getProperties().remove(property);
		return property;
	}
	@Override
	public List<Owner> getAllOwners() {
		
		List<Owner> list = ownerRepo.findAll();
		list.forEach(p->p.getProperties().size());
		return list;
	}
	
	@Override
	public ResponseEntity<String> deleteOwner(Long ownerId) {
		ownerRepo.deleteById(ownerId);
		
		return new ResponseEntity<String>("Owner deleted Successfully", HttpStatus.OK);
	}

	@Override
	public Owner getOwnerByMail(String name) throws resourceNotFoundException {
		return ownerRepo.findByEmail(name).orElseThrow(()->new resourceNotFoundException("Invalid email"));
	}

	@Override
	public Owner getOwnerOfProperty(Long propid) throws resourceNotFoundException {
		Property property = propRepo.findById(propid).orElseThrow(()->new resourceNotFoundException("Propert's not available"));
		return property.getOwner();
	}
	
}
