package com.example.demo.DTO;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.example.demo.DAO.CancelledAppointment;

public class AppointmentDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3407917953997854532L;
	Integer appointmentId;
	String mailId;
	Date startDate;
	Date endDate;
	Boolean repeats;
	String frequency;
	String time;
	String duration;
	List<CancelledAppointment> cancelledAppointments;
	
	
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public List<CancelledAppointment> getCancelledAppointments() {
		return cancelledAppointments;
	}
	public void setCancelledAppointments(List<CancelledAppointment> cancelledAppointments) {
		this.cancelledAppointments = cancelledAppointments;
	}
	public Integer getAppointmentId() {
		return appointmentId;
	}
	public void setAppointmentId(Integer appointmentId) {
		this.appointmentId = appointmentId;
	}
	public String getMailId() {
		return mailId;
	}
	public void setMailId(String mailId) {
		this.mailId = mailId;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Boolean getRepeats() {
		return repeats;
	}
	public void setRepeats(Boolean repeats) {
		this.repeats = repeats;
	}
	public String getFrequency() {
		return frequency;
	}
	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
	
}
