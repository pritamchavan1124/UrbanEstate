package com.app.service;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.app.Entities.Appointment;
import com.app.Entities.AppointmentStatus;
import com.app.Entities.Buyer;
import com.app.Entities.Property;
import com.app.Repository.AppointmentRepository;
import com.app.Repository.BuyerRepository;
import com.app.Repository.propertyRepository;
import com.app.exception.PropertyPolicyException;
import com.app.exception.resourceNotFoundException;

@Service
@Transactional
public class AppointmentServiceImpl implements AppointmentServiceInterface {

	@Autowired
	private AppointmentRepository appRepo;
	
	@Autowired
	private propertyRepository propRepo;
	
	@Autowired
	private BuyerRepository buyerRepo;
	
	@Override
	public ResponseEntity<String> bookAppointment(Long propId, Long buyerId, String date ) throws resourceNotFoundException, PropertyPolicyException {
		
		Property property = propRepo.findById(propId).orElseThrow(()->new resourceNotFoundException("Property's not available"));
		Buyer buyer = buyerRepo.findById(buyerId).orElseThrow(()->new resourceNotFoundException("Buyer's not available"));
		Appointment appointment2 = appRepo.findByBuyerAndProperty(buyer, property);
		if(appointment2==null) {
		Appointment appointment=new Appointment(property, buyer, LocalDate.parse(date) , AppointmentStatus.PENDING);
		appointment.setBuyer(buyer);
		appointment.setProperty(property);
		appRepo.save(appointment);
		}
		else if(appointment2.getStatus()==AppointmentStatus.CANCELLED) {
			appointment2.setStatus(AppointmentStatus.PENDING);
		}
		else
			throw new PropertyPolicyException("Appointment Already booked");
		return new ResponseEntity<String>("Appointment Booked", HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> cancelAppointment(Long apptId) {
		appRepo.cancelAppointment(apptId);
		return new ResponseEntity<String>("Appointment has been cancelled", HttpStatus.OK);
	}

	@Override
	public List<Appointment> showAllAppointmentsOfBuyer(Long buyerId) throws resourceNotFoundException {
		Buyer buyer = buyerRepo.findById(buyerId).orElseThrow(()->new resourceNotFoundException("Buyer's not available"));
		List<Appointment> appList = appRepo.findByBuyerAndStatus(buyer, AppointmentStatus.PENDING);
		return appList;
	}

	
}
