package com.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.app.DTO.BuyerDTO;
import com.app.Entities.Buyer;
import com.app.Entities.Property;
import com.app.exception.PropertyPolicyException;
import com.app.exception.resourceNotFoundException;

public interface BuyerServiceInterface {

	ResponseEntity<String> registerBuyer(BuyerDTO buyer);

	List<Buyer> getAllBuyers();

	ResponseEntity<String> updateBuyer(BuyerDTO buyer) throws resourceNotFoundException;

	ResponseEntity<String> deleteBuyer(Long buyerId);

	ResponseEntity<Property> addPropertyToWishlist(Long buyerId, Long propertyId) throws resourceNotFoundException, PropertyPolicyException;

	ResponseEntity<Property> removeFromWishlist(Long buyerId, Long propertyId) throws resourceNotFoundException;

	List<Property> getAllPropertiesInWishlist(Long buyerId) throws resourceNotFoundException;

	Buyer getBuyerByMail(String name) throws resourceNotFoundException;
	
}
