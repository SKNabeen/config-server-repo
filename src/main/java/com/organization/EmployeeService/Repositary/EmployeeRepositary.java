package com.organization.EmployeeService.Repositary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.organization.EmployeeService.Entity.Employee;

@Repository
public interface EmployeeRepositary extends JpaRepository<Employee, Long>{
	
	//Employee findByFirstName(String firstName);

}
