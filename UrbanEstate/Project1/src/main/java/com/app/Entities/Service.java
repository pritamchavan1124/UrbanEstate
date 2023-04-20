package com.app.Entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Service extends BaseEntity {

	@Column(name = "Service_name",length = 50, nullable = false)
	private String name;
	@Column(name = "vendor_name",length = 50, nullable = false)
	private String vendor;
	private double rate;
	@Column(length = 200)
	private String description;
	@OneToMany(mappedBy = "service", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Service_buyer> services;
}
