package com.app.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.app.DTO.OwnerDTO;
import com.app.DTO.propertyDTO;
import com.app.Entities.Owner;
import com.app.Entities.Property;
import com.app.exception.resourceNotFoundException;

public interface ownerServiceInterface {

	ResponseEntity<Owner> registerOwner(OwnerDTO owner);

	ResponseEntity<String> deleteOwner(Long ownerId);

	List<Owner> getAllOwners();

	Property deletePropertyFromOwner(Long propertyid, Long ownerid) throws resourceNotFoundException;

	Property addPropertyToOwner(propertyDTO property, Long ownerid) throws resourceNotFoundException;

	Owner getOwnerByMail(String name) throws resourceNotFoundException;

	Owner getOwnerOfProperty(Long propid) throws resourceNotFoundException;

	
}
