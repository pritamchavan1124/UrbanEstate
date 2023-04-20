package com.app.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.Entities.OTP;

@Repository
public interface OTPRepositary extends JpaRepository<OTP, String> {

}
