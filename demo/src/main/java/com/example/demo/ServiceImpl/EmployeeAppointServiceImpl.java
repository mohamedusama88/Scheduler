package com.example.demo.ServiceImpl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.AppointmentUtil.AppointmentUtil;
import com.example.demo.DAO.Appointment;
import com.example.demo.DAO.CancelledAppointment;
import com.example.demo.DAO.EmployeeDetails;
import com.example.demo.DTO.AppointmentDto;
import com.example.demo.Repository.AppointmentRepository;
import com.example.demo.Repository.CancelledAppointmentRepository;
import com.example.demo.Repository.EmployeeDetailsRepository;
import com.example.demo.Service.EmployeeAppointService;

@Service
public class EmployeeAppointServiceImpl implements EmployeeAppointService {
	
	@Autowired
	EmployeeDetailsRepository employeeRepository;
	
	@Autowired
	AppointmentRepository appointmentRepository;
	
	@Autowired
	CancelledAppointmentRepository cancelledAppointmentRepository;

	@Override
	@Transactional
	public EmployeeDetails saveEmployee(EmployeeDetails employee) {
		EmployeeDetails existingEmployee=employeeRepository.getEmployeeDetailByMail(employee.getMailId());
		if(existingEmployee!=null)
		{
			employee.setId(existingEmployee.getId());
		}
		if(employee.getAppointments()!=null)
		{
			for(int i=0;i<employee.getAppointments().size();i++)
			{
				employee.getAppointments().get(i).setEmployeedetails(employee);
			}
		}
		employee=employeeRepository.save(employee);
		return employee;
	}

	@Override
	@Transactional
	public EmployeeDetails getEmployee(Integer id) {
		EmployeeDetails employeeAppointment=null;
		try
		{
		employeeAppointment=employeeRepository.findById(id).get();
		}
		catch(Exception e){}
		return employeeAppointment;
	}
	
	@Override
	@Transactional
	public EmployeeDetails getEmployee(String mailId) {
		return employeeRepository.getEmployeeDetailByMail(mailId);
	}
	
	@Override
	@Transactional
	public String deleteEmployeeByMailId(String mailId) {
		EmployeeDetails existingEmployee=employeeRepository.getEmployeeDetailByMail(mailId);
		if(existingEmployee!=null)
		{
			employeeRepository.delete(existingEmployee);
			return "Employee Deleted";
		}
		else
			return "No employee Exist with this Id";
	}

	@Override
	@Transactional
	public void cancelEmployeeAppointmentByDate(Date date,String mailId) {
		
		EmployeeDetails employee=employeeRepository.getEmployeeDetailByMail(mailId);
		if(employee!=null)
		{
			CancelledAppointment cancelledAppointment=new CancelledAppointment();
			cancelledAppointment.setCancelledDate(date);
			cancelledAppointment.setEmployeeDetails(employee);
			cancelledAppointmentRepository.save(cancelledAppointment);
		}
		
	}

	@Override
	public AppointmentDto checkAvailabilityOnDate(String mailId, Date date) {
		
		List<Appointment> appointmentList=appointmentRepository.appointmentForEmployeeOnDate(date, mailId);
		
		for(Appointment appointment : appointmentList)
		{
			if(AppointmentUtil.validateDate(appointment.getStartDate(), date, appointment.getFrequency()) && AppointmentUtil.checkAppointmentFallInRange(appointment, date))
			{
				return AppointmentUtil.convertAppointmentToDto(appointment);
			}
		}
		return null;
	}

}
