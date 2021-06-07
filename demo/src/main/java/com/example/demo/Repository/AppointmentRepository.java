package com.example.demo.Repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.DAO.Appointment;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment,Integer> {
	

	//@Query("from Appointment ap where ap.startDate<=?1 and ap.endDate>=?1 and ap.id not in (select c.appointment.id from CancelledAppointment c where c.cancelledDate=?1) and ap.employeedetails.id not in(select c.employeeDetails.id from CancelledAppointment c where c.cancelledDate=?1)")
	
	@Query("from Appointment ap where ap.startDate<=?1 and ap.endDate>=?1 and ap not in (select c.appointment from CancelledAppointment c where c.cancelledDate=?1) and ap.employeedetails not in (select c.employeeDetails from CancelledAppointment c where c.cancelledDate=?1)")
	public List<Appointment> filterAppointmentByDate(Date date);

	@Query("from Appointment ap where ap.startDate<=?1 and ap.endDate>=?1 and ap.employeedetails.mailId=?2 and ap not in (select c.appointment from CancelledAppointment c where c.cancelledDate=?1) and ap.employeedetails not in (select c.employeeDetails from CancelledAppointment c where c.cancelledDate=?1)")
	public List<Appointment> appointmentForEmployeeOnDate(Date date,String mailId);
}
