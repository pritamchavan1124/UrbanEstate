package com.app.Entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "images, feedback")
@Table(name = "Properties")
public class Property extends BaseEntity{
	
	@Column(length = 50, nullable = false)
	private String name;
	@Column(length = 50)
	private String description;
	@Column(length = 50, nullable = false)
	@Enumerated(EnumType.STRING)
	private Type propType;
	@Column(length = 50, nullable = false)
	private PropertyFor propertyFor;
	@Column(name = "Total_area", nullable = false)
	private double area;
	@Column(name="No_of_Bedrooms",nullable = false)
	private int bedrooms;
	@JsonProperty(access = Access.WRITE_ONLY)
	@OneToMany(mappedBy ="property", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Image> images=new ArrayList<Image>();
//	@OneToOne
//	@JoinColumn(name = "Address_id")
	@Embedded
	private Address address;
	private double price;
	@Column(length = 50)
	@Enumerated(EnumType.STRING)
	private Status status;
	@Column
	private String imageURL;
	@ManyToOne
	@JoinColumn(name = "Owner_id")
	@JsonProperty(access = Access.WRITE_ONLY)
	private Owner owner;
	
	@ManyToOne
	@JoinColumn(name = "Buyer_id")
	@JsonProperty(access = Access.WRITE_ONLY)
	private Buyer buyer;
	
	@Column(length = 300)
	private String amenities;
	@OneToMany(mappedBy = "property", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonProperty(access = Access.WRITE_ONLY)
	private List<Feedback> feedback=new ArrayList<Feedback>();
	private int registrationId;
	
	
	public String addImageToProperty(String imagePath) {
		Image image=new Image(this, imagePath);
		this.images.add(image);
		image.setProperty(this);
		return "Image set successfully";
		}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Property other = (Property) obj;
		return this.getId()== other.getId();
	}
	
	
	
}
