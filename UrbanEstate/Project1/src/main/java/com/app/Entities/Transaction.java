package com.app.Entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Transaction extends BaseEntity{
	
	@Column(name = "Transaction_amount",nullable = false)
	private double amount;
	@Column(name = "Transaction_date")
	@CreationTimestamp
	private LocalDate date;
	
	@ManyToOne
	@JoinColumn(name = "buyer_id")
	private Buyer buyer;
	@OneToOne
	@JoinColumn(name = "property_id")
	private Property property_id;
	
}