package com.app.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "feedbacks")
public class Feedback extends BaseEntity{

	@ManyToOne
	@JoinColumn(name = "propert_id")
	private Property property;
	@Column(nullable = false)
	private int rating;
	@Column(length = 200)
	private String comments;
	
}
