package com.app.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;

import com.app.DTO.ForgotPasswordDTO;
import com.app.DTO.Userdto;
import com.app.exception.resourceNotFoundException;



public interface IUserService {


//	List<Userdto> getAllCustomer();
//
//	Userdto updateExistingUser(Userdto updateUser);
//
//	String deleteUserDetails(DeleteAccountDto account);

	Userdto getUserByEmail(String userEmail);

	Userdto saveNewUser(Userdto userObj);

	ResponseEntity<String> updatePassword(ForgotPasswordDTO forgotPass) throws resourceNotFoundException;

//	Userdto getUserById(int userId);

	

	

}
