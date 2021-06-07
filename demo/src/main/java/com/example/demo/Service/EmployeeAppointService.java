package com.example.demo.Service;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.example.demo.DAO.EmployeeDetails;
import com.example.demo.DTO.AppointmentDto;

@Service
public interface EmployeeAppointService {

	public EmployeeDetails saveEmployee(EmployeeDetails employee);
	public EmployeeDetails getEmployee(Integer id);
	public EmployeeDetails getEmployee(String mailId);
	public String deleteEmployeeByMailId(String mailId);
	public void cancelEmployeeAppointmentByDate(Date date,String mailId);
	public AppointmentDto checkAvailabilityOnDate(String mailId,Date date);
}
