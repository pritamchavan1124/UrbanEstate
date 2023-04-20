package com.app.Entities;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import org.springframework.validation.annotation.Validated;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity 
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "users_tbl") 
@NoArgsConstructor
@Getter
@Setter
@Validated
@ToString

public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JoinColumn(name = "id")
	private Long id;
	@Column(length = 30)
	@Enumerated(EnumType.STRING) 
	private Role userRole;
	@Column(length = 50, unique = true)
	private String email;
	@Column(length = 100)
	private String password;
	@Column(length = 30, nullable = false)
	private String firstName;
	@Column(length = 30, nullable = false)
	private String lastName;
	@Embedded
	private Address address;
	@Column(name = "Mobile_no")
	private Long contactNumber;
}
