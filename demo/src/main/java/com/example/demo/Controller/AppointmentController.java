package com.example.demo.Controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.DTO.AppointmentDto;
import com.example.demo.Service.AppointmentService;

@RestController
public class AppointmentController {
	
	@Autowired
	AppointmentService appointmentService;
	
	@GetMapping("appointments/filter")
	public List<AppointmentDto> filterAppointmentByDate(@RequestParam("date")@DateTimeFormat(pattern="yyyy-MM-dd") Date date)
	{
		return appointmentService.filterAppointmentByDate(date);
	}
	
	@GetMapping("appointments/{id}")
	public AppointmentDto getAppointmentById(@PathVariable("id") Integer id)
	{
	
		return appointmentService.getAppointmentById(id);
	}
	
	@PutMapping("appointments/{id}")
	public AppointmentDto updateAppointmentById(@PathVariable("id") Integer id, @RequestBody AppointmentDto appointment)
	{
		appointment.setAppointmentId(id);
		return appointmentService.updateOrSaveAppointmentById(appointment);
	}
	
	@PostMapping("appointment")
	public AppointmentDto saveAppointment(@RequestBody AppointmentDto appointment)
	{
		return appointmentService.updateOrSaveAppointmentById(appointment);
	}
	
	@DeleteMapping("appointments/{id}")
	public String saveAppointment(@PathVariable("id") Integer id)
	{
		Boolean status=appointmentService.deleteAppointmentById(id);
		if(status)
			return "Appointment Deleted Successfully";
		else
			return "Invalid Appointment Id";
	}
	
	@PostMapping("appointments/{id}/cancel")
	public void cancelAppointmentOnDate(@RequestParam("date")@DateTimeFormat(pattern="yyyy-MM-dd") Date date,@PathVariable("id") Integer id)
	{
		appointmentService.cancelAppointmentOnDate(date, id);
	}
	@PostMapping("appointments/cancel")
	public void cancelAllAppointmentOnDate(@RequestParam("date")@DateTimeFormat(pattern="yyyy-MM-dd") Date date)
	{
		appointmentService.cancelAllAppointmentOnDate(date);
	}
	

}
