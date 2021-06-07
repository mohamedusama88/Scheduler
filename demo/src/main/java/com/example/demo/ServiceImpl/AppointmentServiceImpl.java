package com.example.demo.ServiceImpl;

import java.util.ArrayList;
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
import com.example.demo.Service.AppointmentService;

@Service
public class AppointmentServiceImpl implements AppointmentService {
	
	@Autowired
	AppointmentRepository appointmentRepository;
	
	@Autowired
	EmployeeDetailsRepository employeeAppointmentRepository;
	
	@Autowired
	CancelledAppointmentRepository cancelledAppointmentRepository;

	
	@Transactional
	@Override
	public List<AppointmentDto> filterAppointmentByDate(Date date){
	
		List<Appointment> list=appointmentRepository.filterAppointmentByDate(date);
		List<AppointmentDto> filteredList=new ArrayList<AppointmentDto>();
		CancelledAppointment cancelledAppointment=cancelledAppointmentRepository.getCancellendAppointmentDate(date);
		
		if(cancelledAppointment==null || cancelledAppointment.getId()==null)
		{
		
		for(int i=0;i<list.size();i++)
		{
			if (date.compareTo(list.get(i).getStartDate())>0 && date.compareTo(list.get(i).getEndDate())<0 && AppointmentUtil.validateDate(list.get(i).getStartDate(),date,list.get(i).getFrequency()))
			{
				filteredList.add(AppointmentUtil.convertAppointmentToDto(list.get(i)));
			}
		}
		}
		return filteredList;
	}
	
	@Transactional
	@Override
	public AppointmentDto getAppointmentById(Integer id) {
		
		AppointmentDto appointment=null;
		try
		{
			appointment=AppointmentUtil.convertAppointmentToDto(appointmentRepository.findById(id).get());
		}
		catch(Exception e)
		{}
		return appointment;
	}
	
	@Transactional
	@Override
	public AppointmentDto updateOrSaveAppointmentById(AppointmentDto appointmentDto) {
		
	
		Appointment appointment=null;
		if(appointmentDto.getAppointmentId()!=null)
		{
			try
			{
			appointment=appointmentRepository.findById(appointmentDto.getAppointmentId()).get();
			}
			catch(Exception e)
			{}
			if(appointment!=null)
			{
				appointment=AppointmentUtil.compareAndUpdate(appointment,appointmentDto);
			}
		}
		else
		{
			appointment=AppointmentUtil.convertDtoToAppointment(appointmentDto);
		}
		
		if(appointmentDto.getMailId()!=null && appointment!=null)
		{
			appointment.setEmployeedetails(employeeAppointmentRepository.getEmployeeDetailByMail(appointmentDto.getMailId()));
			if(appointment.getEmployeedetails()==null)
			{
				EmployeeDetails employee=new EmployeeDetails();
				employee.setMailId(appointmentDto.getMailId());
				employeeAppointmentRepository.save(employee);
				appointment.setEmployeedetails(employee);
			}
		}
			
		if(AppointmentUtil.validateAppointment(appointment))
			appointmentRepository.save(appointment);
		else
			return null;
		
		
		return AppointmentUtil.convertAppointmentToDto(appointment);
	}
	
	
	@Transactional
	@Override
	public Boolean deleteAppointmentById(Integer id) {
		
		
		if(appointmentRepository.findById(id).isPresent())
		{
			appointmentRepository.deleteById(id);
			return true;
		}
		else
			return false;
	}

	@Transactional
	@Override
	public void cancelAllAppointmentOnDate(Date date) {
		CancelledAppointment cancelledAppointment=new CancelledAppointment();
		cancelledAppointment.setCancelledDate(date);
		
		cancelledAppointmentRepository.save(cancelledAppointment);
		
	}

	@Transactional
	@Override
	public void cancelAppointmentOnDate(Date date, Integer id) {
		try
		{
		Appointment appointment=appointmentRepository.findById(id).get();
		if(AppointmentUtil.validateDate(appointment.getStartDate(), date, appointment.getFrequency()))
		{
		CancelledAppointment cancelledAppointment=new CancelledAppointment();
		cancelledAppointment.setCancelledDate(date);
		cancelledAppointment.setAppointment(appointment);
		cancelledAppointmentRepository.save(cancelledAppointment);
		}
		}
		catch(Exception e)
		{}
	}

}
