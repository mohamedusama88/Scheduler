package com.example.demo.Repository;



import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.DAO.EmployeeDetails;

@Repository
public interface EmployeeDetailsRepository extends JpaRepository<EmployeeDetails,Integer>
{
	
	@Query("from EmployeeDetails e where e.mailId=?1")
	public EmployeeDetails getEmployeeDetailByMail(String mailId);
	
	//@Query("from EmployeeDetails e where e.appointment.startDate<=date and e.appointment.endDate>=date")
	//public List<EmployeeDetails getEmployeeByDateRange(Date date);
	

	
	

}
