package com.app.DTO;

import com.app.Entities.Address;
import com.app.Entities.PropertyFor;
import com.app.Entities.Role;
import com.app.Entities.Status;
import com.app.Entities.Type;

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
public class OwnerDTO {

	private Role userRole;
	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private Long contactNumber;
	private Address address;
}
