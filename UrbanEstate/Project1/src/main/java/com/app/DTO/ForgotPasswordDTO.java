package com.app.DTO;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Validated
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ForgotPasswordDTO {

	@Email
	private String email;
	@NotNull
	private String oldPassword;
	@NotNull
	private String newPassword;
}
