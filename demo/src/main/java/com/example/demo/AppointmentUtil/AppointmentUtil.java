package com.example.demo.AppointmentUtil;

import java.util.Calendar;
import java.util.Date;

import com.example.demo.DAO.Appointment;
import com.example.demo.DTO.AppointmentDto;

public class AppointmentUtil {
	
	public static boolean validateDate(Date startDate,Date date,String frequency)
	{
		Calendar c = Calendar. getInstance(); 
		c. setTime(date);
		Calendar c1 = Calendar. getInstance();
		c1. setTime(startDate);
		
		switch(frequency)
		{
		case "Daily" :
			return true;
		case "Monthly" :
			return startDate.getDate()==date.getDate();
		case "Weekly" :
			return c.get(Calendar.DAY_OF_WEEK)==c1.get(Calendar.DAY_OF_WEEK);
		case "Weekdays" :
			return  (c.get(Calendar.DAY_OF_WEEK)!=1 && c.get(Calendar.DAY_OF_WEEK)!=7);
		}
		return false;
		
	}
	
	public static AppointmentDto convertAppointmentToDto(Appointment appointment)
	{
		if(appointment==null)
			return null;
		
		AppointmentDto appointmentDto=new AppointmentDto();
		appointmentDto.setAppointmentId(appointment.getId());
		appointmentDto.setStartDate(appointment.getStartDate());
		appointmentDto.setEndDate(appointment.getEndDate());
		appointmentDto.setFrequency(appointment.getFrequency());
		appointmentDto.setRepeats(appointment.getRepeats());
		appointmentDto.setTime(appointment.getTime());
		appointmentDto.setDuration(appointment.getDuration());
		appointmentDto.setCancelledAppointments(appointment.getCancelledAppointments());
		if(appointment.getEmployeedetails()!=null)
			appointmentDto.setMailId(appointment.getEmployeedetails().getMailId());
		return appointmentDto;
	}
	
	public static Appointment convertDtoToAppointment(AppointmentDto appointmentDto)
	{
		if(appointmentDto==null)
			return null;
		
		Appointment appointment=new Appointment();
		appointment.setId(appointmentDto.getAppointmentId());
		appointment.setStartDate(appointmentDto.getStartDate());
		appointment.setEndDate(appointmentDto.getEndDate());
		appointment.setFrequency(appointmentDto.getFrequency());
		appointment.setRepeats(appointmentDto.getRepeats());
		appointment.setTime(appointmentDto.getTime());
		appointment.setDuration(appointmentDto.getDuration());
		return appointment;
	}
	
	public static boolean validateAppointment(Appointment appointment)
	{
		if(appointment.getStartDate()==null || appointment.getEndDate()==null || appointment.getStartDate().compareTo(appointment.getEndDate())>0)
			return false;
		if(appointment.getFrequency()==null)
			return false;
		return true;
	}
	
	public static Appointment compareAndUpdate(Appointment appointment, AppointmentDto appointmentDto)
	{
		if(appointmentDto.getStartDate()!=null)
			appointment.setStartDate(appointmentDto.getStartDate());
		if(appointmentDto.getEndDate()!=null)
			appointment.setEndDate(appointmentDto.getEndDate());
		if(appointmentDto.getRepeats()!=null)
			appointment.setRepeats(appointmentDto.getRepeats());
		if(appointmentDto.getFrequency()!=null)
			appointment.setFrequency(appointmentDto.getFrequency());
		if(appointmentDto.getTime()!=null)
			appointment.setTime(appointmentDto.getTime());
		if(appointmentDto.getDuration()!=null)
			appointment.setDuration(appointmentDto.getDuration());
		return appointment;
	}
	public static Boolean checkAppointmentFallInRange(Appointment appointment,Date date)
	{
		if(appointment==null || appointment.getDuration()==null || appointment.getTime()==null)
			return false;

	Date startTime=new Date(date.getYear(),date.getMonth(),date.getDate());
	startTime.setHours(Integer.parseInt(appointment.getTime().split(":")[0]));
	startTime.setMinutes(Integer.parseInt(appointment.getTime().split(":")[1]));
	
	
	Date endTime=new Date(date.getYear(),date.getMonth(),date.getDate());
	endTime.setHours(Integer.parseInt(appointment.getTime().split(":")[0]));
	endTime.setMinutes(Integer.parseInt(appointment.getTime().split(":")[1]));
	Integer duration=Integer.parseInt(appointment.getDuration())+endTime.getMinutes();
	endTime.setHours(endTime.getHours()+duration/60);
	endTime.setMinutes(endTime.getMinutes()+duration%60);
	System.out.println(startTime);
	System.out.println(endTime);
	System.out.println(date);
	return date.getTime()>=startTime.getTime() && date.getTime()<=endTime.getTime();
	}
}
