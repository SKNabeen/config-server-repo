package com.organization.EmployeeService.service;

import com.organization.EmployeeService.Dto.APIResponseDto;
import com.organization.EmployeeService.Dto.EmployeeDto;

public interface EmployeeService {
	
	//EmployeeDto saveEmployee(EmployeeDto employeeDto);

	EmployeeDto saveEmployee(EmployeeDto employeeDto);
	
	//APIResponseDto getByName(String firstName);

	APIResponseDto getById(Long id);

}
