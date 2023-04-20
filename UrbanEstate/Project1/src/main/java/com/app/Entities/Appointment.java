package com.app.Entities;

import java.time.LocalDate;

import javax.persistence.CascadeType;
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
public class Appointment extends BaseEntity {

	
	@ManyToOne
	@JoinColumn(name = "property_id")
	private Property property;
	@ManyToOne
	@JoinColumn(name = "buyer_id")
	private Buyer buyer;
	@Column(name="Appointment_date")
	private LocalDate date;
	@Enumerated(EnumType.STRING)
	@Column(length = 30)
	private AppointmentStatus status;
}