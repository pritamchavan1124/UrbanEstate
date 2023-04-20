package com.app.Entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

	public class Address{
		@Column(length =60)
		private String line1;
		@Column(length =60)
		private String line2;
		@Column(length =50)
		private String city;
		@Column(length = 60)
		private String state;
		private int pincode;
}

