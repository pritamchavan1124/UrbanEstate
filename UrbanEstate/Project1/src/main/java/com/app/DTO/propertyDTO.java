package com.app.DTO;

import com.app.Entities.Address;
import com.app.Entities.PropertyFor;
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
public class propertyDTO {
	private Long id;
	private String name;
	private String description;
	private Type propType;
	private PropertyFor propertyFor;
	private double area;
	private int bedrooms;
	private Address address;
	private double price;
	private Status status;
	private String amenities;
	private int registrationId;
	private String imageURL;

	
	
}
