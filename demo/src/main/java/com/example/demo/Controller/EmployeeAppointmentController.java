package com.example.demo.Controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DAO.EmployeeDetails;
import com.example.demo.DTO.AppointmentDto;
import com.example.demo.Service.AppointmentService;
import com.example.demo.Service.EmployeeAppointService;

@RestController
public class EmployeeAppointmentController {
	

	@Autowired
	EmployeeAppointService employeeAppointment;
	
	@Autowired
	AppointmentService appointmentService;
	
	@GetMapping("employee/{mailId}")
	public EmployeeDetails getEmployeeDetailsBymail(@PathVariable("mailId") String mailId)
	{
		return employeeAppointment.getEmployee(mailId);
	}
	

	@DeleteMapping("employee/{mailId}")
	public String deleteEmployeeDetailsBymailId(@PathVariable("mailId") String mailId)
	{
		return employeeAppointment.deleteEmployeeByMailId(mailId);
	}
	
	@PostMapping("employee")
	public EmployeeDetails saveEmployeeDetails(@RequestBody EmployeeDetails detail)
	{
		return employeeAppointment.saveEmployee(detail);
	}
	
	@PostMapping("employee/{mailId}/appointments/cancel")
	public void cancelEmployeeAppointmentsByDate(@RequestParam("date")@DateTimeFormat(pattern="yyyy-MM-dd") Date date,@PathVariable("mailId") String mailId)
	{
		employeeAppointment.cancelEmployeeAppointmentByDate(date, mailId);
	}
	
	@GetMapping("employee/{mailId}/availability")
	public AppointmentDto checkAvailabilityOnDate(@RequestParam("date")@DateTimeFormat(pattern="yyyy-MM-dd") Date date, @PathVariable("mailId") String mailId,@RequestParam("hour") Integer hour,@RequestParam("minute") Integer minute)
	{
		date.setHours(hour);
		date.setMinutes(minute);
		AppointmentDto dto=new AppointmentDto();
		dto=employeeAppointment.checkAvailabilityOnDate(mailId, date);
		return dto;
	}

}
