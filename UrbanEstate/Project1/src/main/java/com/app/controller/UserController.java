package com.app.controller;

import java.time.LocalTime;
import java.util.Random;

import javax.annotation.security.PermitAll;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.DTO.ForgotPasswordDTO;
import com.app.DTO.NewPasswordDto;
import com.app.DTO.Userdto;
import com.app.Entities.AuthResp;
import com.app.Entities.Buyer;
import com.app.Entities.OTP;
import com.app.Entities.Owner;
import com.app.Entities.Role;
import com.app.Entities.User;
import com.app.Jwt_utils.JwtUtils;
import com.app.Repository.BuyerRepository;
import com.app.Repository.UserRepositiory;
import com.app.Repository.ownerRepository;
import com.app.exception.resourceNotFoundException;
import com.app.service.IEmailSendingService;
import com.app.service.IUserService;
import com.app.service.OTPService;

import lombok.extern.slf4j.Slf4j;
@CrossOrigin(origins = "http://localhost:3000")
@RestController

@RequestMapping("/user")
@Slf4j
public class UserController {
	
	//dependency injection
	@Autowired
	private IUserService userService;
	@Autowired
	private BuyerRepository buyerRepo;
	@Autowired
	private ownerRepository ownerRepo;
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private IEmailSendingService emailService;
	
	@Autowired
	private OTPService otpService;

	@Autowired
	private JwtUtils utils;
	
	@Autowired
	private UserRepositiory userRepo;
	
	@Autowired
	private PasswordEncoder encoder;
	
	Random random = new Random(1000);
	
	
	@PermitAll
	@GetMapping("/forgotpassword")
	public ResponseEntity<?> forgotPassord(@RequestParam("email") String email) {

//		log.info("In user controller forgot password" + email);
		int OTP1 = random.nextInt(9999) + LocalTime.now().getMinute() + LocalTime.now().getSecond();
//		log.info("OTP for" + OTP1 + " for email is :" + email);
		
		// check given email is exist in your data base or not
		Userdto userByEmail = userService.getUserByEmail(email);
		if (userByEmail != null) {
			OTP otp = new OTP();
			otp.setEmail(email);
			
			otp.setOtp(OTP1);
			// create subject and message for email
			String to = email;
			String subject = "OTP from UrbanEstate";
			String msg = "<h1>Your OTP is:" + OTP1 + " ,DO NOT SHARE WITH ANY ONE</h1>";
			emailService.sendEmail(to, msg, subject);
			otpService.setOTP(otp);
			return ResponseEntity.ok("OTP sent to your email");
		} else {
			return new ResponseEntity<>("Invaild email", HttpStatus.NOT_FOUND);

		}

	}
	
	@PermitAll
	@GetMapping("/verify")
	public ResponseEntity<?> verifyOTP(@RequestParam int otp, @RequestParam String email) throws resourceNotFoundException {
//		log.info("In verify OTP method");
		String sendEmail = email;
		System.out.println(otp + "   " + email);
		OTP otp2 = otpService.getOTP(email);
		// checking time boundation of 3 minutes
		LocalTime t2 = LocalTime.now();
		LocalTime t1 = otp2.getTimeStamp();
		LocalTime plusMinutes = t1.plusMinutes(3);
//		log.info(" " + plusMinutes);
		if ((otp == otp2.getOtp())) {
			if (t2.isBefore(plusMinutes)) {
				Userdto user = userService.getUserByEmail(sendEmail);
//				String token=utils.generateJwtToken(mapper.map(user, User.class));
				String token=utils.generateJwtToken(mapper.map(user, User.class));
				System.out.println(token+"========================");
				return ResponseEntity.ok(
                        new AuthResp("Auth successful!", utils.generateJwtToken(mapper.map(user, User.class)), user));
			} else {
				otpService.deleteById(sendEmail);
				return new ResponseEntity<>("OUT OF TIME", HttpStatus.FORBIDDEN);
			}

		} else {
			return new ResponseEntity<>("Wrong OTP", HttpStatus.FORBIDDEN);
		}
	}
	
	@PostMapping("/newpassword")
	public String newPassword(@RequestBody @Valid NewPasswordDto newpassDto) {
		Userdto user = userService.getUserByEmail(newpassDto.getEmail());
		User newUser = mapper.map(user, User.class);
		if(newUser.getUserRole()==Role.ROLE_BUYER) {
		Buyer buyer = buyerRepo.findById(newUser.getId()).get();
		buyer.setPassword(encoder.encode(newpassDto.getNewPassword()));
		buyerRepo.save(buyer);
		}
		if(newUser.getUserRole()==Role.ROLE_OWNER) {
			Owner owner = ownerRepo.findById(newUser.getId()).get();
			owner.setPassword(encoder.encode(newpassDto.getNewPassword()));
			ownerRepo.save(owner);
			}
		otpService.deleteById(newpassDto.getEmail());
		return "Password changed successfully....";
	}

	@PutMapping("/updatepassword")
	public ResponseEntity<String> updatePassword(@RequestBody ForgotPasswordDTO forgotPass) throws resourceNotFoundException{
		return userService.updatePassword(forgotPass);
	}
}
