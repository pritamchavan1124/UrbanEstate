package com.app.DTO;

import com.app.Entities.Address;
import com.app.Entities.Role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BuyerDTO {

	private Role userRole;
	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private Address address;
	private long contactNumber;

}
