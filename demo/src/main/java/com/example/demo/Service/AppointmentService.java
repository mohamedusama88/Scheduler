package com.example.demo.Service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.DAO.Appointment;
import com.example.demo.DTO.AppointmentDto;

@Service
public interface AppointmentService {

	
	public List<AppointmentDto> filterAppointmentByDate(Date date);
	
	public AppointmentDto getAppointmentById(Integer id);
	
	public AppointmentDto updateOrSaveAppointmentById(AppointmentDto appointment);
	
	public Boolean deleteAppointmentById(Integer id);
	
	public void cancelAllAppointmentOnDate(Date date);
	
	public void cancelAppointmentOnDate(Date date,Integer id);

}
