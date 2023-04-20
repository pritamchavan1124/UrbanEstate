package com.app.service;

import com.app.Entities.OTP;
import com.app.exception.resourceNotFoundException;

public interface OTPService {

	// set Otp
	boolean setOTP(OTP otp);

	// getOtp
	OTP getOTP(String email) throws resourceNotFoundException;

	boolean deleteById(String email);
}
