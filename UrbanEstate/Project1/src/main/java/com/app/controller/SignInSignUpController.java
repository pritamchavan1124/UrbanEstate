
package com.app.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.DTO.BuyerDTO;
import com.app.DTO.OwnerDTO;
import com.app.DTO.Userdto;
import com.app.Entities.AuthRequest;
import com.app.Entities.AuthResp;
import com.app.Jwt_utils.JwtUtils;
import com.app.service.BuyerServiceImpl;
import com.app.service.UserServiceImplement;
import com.app.service.adminServiceImpl;
import com.app.service.ownerServiceImpl;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/auth")
@Slf4j
public class SignInSignUpController {
	
	//user services dependency injected
	@Autowired
	private ownerServiceImpl ownerServ;
	@Autowired
	private BuyerServiceImpl buyerServ;
	@Autowired
	private JwtUtils utils;
	@Autowired
	private UserServiceImplement userService;
	@Autowired
	private adminServiceImpl adminServ;
	
	// dep : Auth mgr
	@Autowired
	private AuthenticationManager manager;
	
	@PostMapping("/user/signup")
	public ResponseEntity<?> userRegistration(@RequestBody @Valid Userdto userObj,BindingResult bindingRelt){
		
		// invoke service layer method
		
		 return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveNewUser(userObj));
		
	}
	
	@PostMapping("/owner/signup")
	public ResponseEntity<?> OwnerRegistration(@RequestBody @Valid OwnerDTO owner,BindingResult br){
		if(br.hasErrors()) {
			return  ResponseEntity.badRequest().body(br.getAllErrors());// :
		}
		System.out.println("ajinkya");
		// invoke service layer method , for saving : user info + associated roles info
		return ResponseEntity.status(HttpStatus.CREATED).body(ownerServ.registerOwner(owner) );
	}
	
	@PostMapping("/buyer/signup")
	public ResponseEntity<?> BuyerRegistration(@RequestBody @Valid BuyerDTO buyer) {
																													// :
		// invoke service layer method , for saving : user info + associated roles info
		return ResponseEntity.status(HttpStatus.CREATED).body(buyerServ.registerBuyer(buyer));
	}
	
	@PostMapping("/signin")
	public ResponseEntity<?> validateUserCreateToken(@RequestBody @Valid AuthRequest request,BindingResult br){
		// store incoming user details(not yet validated) into Authentication object
		// Authentication i/f ---> imple by UserNamePasswordAuthToken
		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(request.getEmail(),
				request.getPassword());
//		log.info("auth token " + authToken);
		try {
			// authenticate the credentials
			Authentication authenticatedDetails = manager.authenticate(authToken);
			Userdto user = userService.getUserByEmail(authenticatedDetails.getName());		
			return ResponseEntity
					.ok(new AuthResp("Auth successful!", utils.generateJwtToken(authenticatedDetails), user));
		} catch (BadCredentialsException e) {
			System.out.println("err " + e); 
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
		}

	}

}
