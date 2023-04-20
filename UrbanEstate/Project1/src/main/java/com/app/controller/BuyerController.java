package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.DTO.BuyerDTO;
import com.app.Entities.Buyer;
import com.app.Entities.Property;
import com.app.exception.PropertyPolicyException;
import com.app.exception.resourceNotFoundException;
import com.app.service.BuyerServiceImpl;

@RestController
@RequestMapping("/buyer")
@CrossOrigin(origins = "http://localhost:3000")
public class BuyerController {

	@Autowired
	private BuyerServiceImpl buyerServ;
	
	@PostMapping
	public ResponseEntity<String> registerBuyer(@RequestBody BuyerDTO buyer){
		return buyerServ.registerBuyer(buyer);
	}
	
	@GetMapping
	public List<Buyer> getAllBuyers(){
		return buyerServ.getAllBuyers();
	} 
	
	@PutMapping
	public ResponseEntity<String> updateBuyer(@RequestBody BuyerDTO buyer) throws resourceNotFoundException{
		return buyerServ.updateBuyer(buyer);
	}
	
	@DeleteMapping("/{buyerId}")
	public ResponseEntity<String> deleteBuyer(@PathVariable Long buyerId){
		return buyerServ.deleteBuyer(buyerId);
	}
	
	@GetMapping("/property/addwishlist/{buyerId}/{propertyId}")
	public ResponseEntity<Property> addPropertyToWishlist(@PathVariable Long buyerId, @PathVariable Long propertyId) throws resourceNotFoundException, PropertyPolicyException{
		return buyerServ.addPropertyToWishlist(buyerId, propertyId);
	}
	
	@GetMapping("/property/removewishlist/{buyerId}/{propertyId}")
	public ResponseEntity<Property> removeFromWishlist(@PathVariable Long buyerId, @PathVariable Long propertyId) throws resourceNotFoundException, PropertyPolicyException{
		return buyerServ.removeFromWishlist(buyerId, propertyId);
	}
	
	@GetMapping("/property/getWishlist/{buyerId}")
	public List<Property> getAllPropertiesInWishlist(@PathVariable Long buyerId) throws resourceNotFoundException{
		return buyerServ.getAllPropertiesInWishlist(buyerId);
	}
	
 }
