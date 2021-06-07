package com.example.demo.Repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.DAO.CancelledAppointment;

@Repository
public interface CancelledAppointmentRepository extends JpaRepository<CancelledAppointment, Integer> {
	
	@Query("from CancelledAppointment c where c.cancelledDate=?1 and c.appointment is null and c.employeeDetails is null")
	public CancelledAppointment getCancellendAppointmentDate(Date cancelledDate);

}
