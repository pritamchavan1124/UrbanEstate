package com.app.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.app.Entities.Appointment;
import com.app.exception.PropertyPolicyException;
import com.app.exception.resourceNotFoundException;

public interface AppointmentServiceInterface {

	ResponseEntity<String> bookAppointment(Long propId, Long buyerId, String date) throws resourceNotFoundException, PropertyPolicyException;

	ResponseEntity<String> cancelAppointment(Long apptId);

	List<Appointment> showAllAppointmentsOfBuyer(Long buyerId) throws resourceNotFoundException;



}
