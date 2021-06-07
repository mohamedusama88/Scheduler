package com.example.demo.DAO;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Table(name="appointment")
@Entity
@JsonIgnoreProperties(value = { "employeedetails","cancelledAppointments" })
public class Appointment implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	
	@Column(name="startDate")
	Date startDate;
	
	@Column(name="endDate")
	Date endDate;
	
	@Column(name="time")
	String time;
	
	@Column(name="duration")
	String duration;
	
	@Column(name="repeats")
	Boolean repeats;
	
	@Column(name="frequency")
	String frequency;
	
	
	
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

	@ManyToOne
	@JoinColumn(name="empId",nullable=false)
	EmployeeDetails employeedetails;
	

	@OneToMany(mappedBy="appointment",cascade = CascadeType.ALL)
	List<CancelledAppointment> cancelledAppointments;
	

	public List<CancelledAppointment> getCancelledAppointments() {
		return cancelledAppointments;
	}

	public void setCancelledAppointments(List<CancelledAppointment> cancelledAppointments) {
		this.cancelledAppointments = cancelledAppointments;
	}

	public EmployeeDetails getEmployeedetails() {
		return employeedetails;
	}

	public void setEmployeedetails(EmployeeDetails employeedetails) {
		this.employeedetails = employeedetails;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	
	
	

}
