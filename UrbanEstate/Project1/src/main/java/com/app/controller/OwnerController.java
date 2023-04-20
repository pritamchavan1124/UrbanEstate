package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.DTO.OwnerDTO;
import com.app.DTO.propertyDTO;
import com.app.Entities.Owner;
import com.app.Entities.Property;
import com.app.exception.resourceNotFoundException;
import com.app.service.ownerServiceImpl;

@RestController
@RequestMapping("/owner")
@CrossOrigin(origins = "http://localhost:3000")
public class OwnerController {


	@Autowired
	private ownerServiceImpl ownerServ;
	
	@PostMapping
	public ResponseEntity<Owner> registerOwner(@RequestBody OwnerDTO owner){
		return ownerServ.registerOwner(owner);
	}
	
	@GetMapping("/get/{propid}")
	public Owner getOwnerOfProperty(@PathVariable Long propid) throws resourceNotFoundException {
		System.out.println("In owner controller");
		return ownerServ.getOwnerOfProperty(propid);
	}
	
	@PostMapping("/property/{ownerid}")
	public Property addPropertyToOwner(@RequestBody propertyDTO property, @PathVariable Long ownerid) throws resourceNotFoundException{
		return ownerServ.addPropertyToOwner(property, ownerid);
	}
	
	@DeleteMapping("/property/{ownerid}/{propertyid}")
	public Property deletePropertyFromOwner(@PathVariable Long propertyid, @PathVariable Long ownerid) throws resourceNotFoundException {
		return ownerServ.deletePropertyFromOwner(propertyid, ownerid);
	}
	
	@GetMapping
	public List<Owner> getAllOwners(){
		return ownerServ.getAllOwners();
	}
	
	@DeleteMapping("{ownerId}")
	public ResponseEntity<String> deleteOwner(@PathVariable Long ownerId){
		return ownerServ.deleteOwner(ownerId);
	}
//	@PutMapping("/property/{ownerid}")
}
