package com.app.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.app.Entities.Appointment;
import com.app.Entities.AppointmentStatus;
import com.app.Entities.Buyer;
import com.app.Entities.Property;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

Appointment findByBuyerAndProperty(Buyer buyer, Property property);

@Modifying
@Query("update Appointment a set a.status='CANCELLED' where a.id=?1")
void cancelAppointment(Long apptId);

List<Appointment> findByBuyerAndStatus(Buyer buyer, AppointmentStatus status);

List<Appointment> findByBuyer(Buyer buyer);

}
