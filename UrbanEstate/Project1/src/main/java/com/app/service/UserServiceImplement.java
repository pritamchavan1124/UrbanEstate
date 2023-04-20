package com.app.service;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.DTO.ForgotPasswordDTO;
import com.app.DTO.Userdto;
import com.app.Entities.User;
import com.app.Repository.UserRepositiory;
import com.app.exception.resourceNotFoundException;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class UserServiceImplement implements IUserService {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private UserRepositiory userRepository;
	
	@Override
	public Userdto saveNewUser(Userdto userObj) {
	
		log.info("In user service implimentation : Save user ");
		// map dto --> entity
		User user = mapper.map(userObj, User.class);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		User addedUser=userRepository.save(user);
		// map entity --> dto
		return mapper.map(addedUser, Userdto.class);
	}
	
	
	
	//add method for update the new user
//	@Override
//	public Userdto updateExistingUser(Userdto updateUser) {
//		
//		log.info("In user service implimentation : update user ById ");
//		
//		Optional<User> user = userRepository.findByEmail(updateUser.getEmail());
//		if(user.get() != null) {
//			user.get().setEmail(updateUser.getEmail());
//			user.get().setFirstName(updateUser.getFirstName());
//			user.get().setLastName(updateUser.getLastName());
//			user.get().setContactNumber(updateUser.getContactNumber());
//			user.get().setDOB(updateUser.getDOB());
//			user.get().setUserRole(updateUser.getUserRole());
//			User persistance=userRepository.save(user.get());
//			return mapper.map(persistance, Userdto.class);
////			 User updatedUser = mapper.map(updateUser, User.class);
////			 userRepository.save(updatedUser);
//			 
//		}else
//		{
//			throw new UserHandlingException("Invalid Email id");
//		}
//		
//	}
//
//	@Override
//	public String deleteUserDetails(DeleteAccountDto account) {
//		String mesg = "Deletion of user details failed!!!!!!!!!!!";
//		User findUser = userRepository.findByEmail(account.getEmail())
//				.orElseThrow(() -> new UserHandlingException("Invalid user Email"));
//		if (passwordEncoder.matches(account.getOldPassword(), findUser.getPassword())) {
//			cartService.deleteByUser(findUser.getId());
//			userRepository.deleteById(findUser.getId());
//			mesg = "user details deleted successfully , for User id :" + findUser.getId();
//		}
//		return mesg;
//
//	}

	@Override
	public Userdto getUserByEmail(String userEmail) {
		User user = userRepository.findByEmail(userEmail).orElseThrow(() -> new RuntimeException("User Not Found"));
		return mapper.map(user, Userdto.class);
	}



	@Override
	public ResponseEntity<String> updatePassword(ForgotPasswordDTO forgotPass) throws resourceNotFoundException {
		User user = userRepository.findByEmail(forgotPass.getEmail()).orElseThrow(()->new resourceNotFoundException("Invalid Email"));
		user.setPassword(passwordEncoder.encode(forgotPass.getNewPassword()));
		return new ResponseEntity<String>("Password updated", HttpStatus.OK);
	}

//	@Override
//	public Userdto getUserById(int userId) {
//		// TODO Auto-generated method stub
//		return null;
//	} 
}
