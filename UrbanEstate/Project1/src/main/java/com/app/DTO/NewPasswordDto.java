package com.app.DTO;

import com.sun.istack.NotNull;

//@Setter
//@Getter
public class NewPasswordDto {

	@NotNull
	private String email;

	@NotNull
	private String newPassword;

	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	
	
}
