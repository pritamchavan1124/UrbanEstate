package com.app.DTO;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Pattern;

import org.springframework.validation.annotation.Validated;

import com.app.Entities.Address;
import com.app.Entities.Role;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Validated
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Userdto {

	@JsonProperty(access = Access.READ_ONLY)
	private Long id;
	
	@Column(length = 50, unique = true)
	private String email;
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,20}$", message = "Invalid Password")
	@Column(length = 50)
	private String password;
	@Column(length = 30, nullable = false)
	private String firstName;
	@Column(length = 30, nullable = false)
	private String lastName;
	@Embedded
	private Address address;
	@Column(name = "Mobile_no")
	private Long contactNumber;
	@Column(nullable = false)
	private Role userRole;
}
