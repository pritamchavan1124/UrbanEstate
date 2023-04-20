package com.app.Entities;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Service_buyer extends BaseEntity {

	@ManyToOne
	@JoinColumn(name = "Service_id")
	private Service service;
	@ManyToOne
	@JoinColumn(name = "Buyer_id")
	private Buyer buyer;
	@Enumerated(EnumType.STRING)
	@Column(length = 30)
	private SeviceStatus status;
	@ManyToOne
	@JoinColumn(name = "property_id")
	private Property property;
	private LocalDate startDate;
//	@OneToOne
//	@JoinColumn(name = "transaction_id")
//	private Transaction transaction;
}
