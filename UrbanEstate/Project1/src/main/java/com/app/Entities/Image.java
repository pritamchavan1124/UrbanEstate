package com.app.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Image extends BaseEntity {
	
	@ManyToOne
	@JoinColumn(name = "property_id")
	private Property property;
	@Column(length = 100, name = "image_url")
	private String imageurl;
}
