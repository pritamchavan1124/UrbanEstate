package com.app.Entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Wishlist extends BaseEntity {
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "Property_Wishlist", 
	joinColumns = @JoinColumn(name="Wishlist_id"),
	inverseJoinColumns = @JoinColumn(name="property_id"))
	private List<Property> properties=new ArrayList<Property>();
	@OneToOne
	@JoinColumn(name = "buyer_id")
	private Buyer buyer;

}