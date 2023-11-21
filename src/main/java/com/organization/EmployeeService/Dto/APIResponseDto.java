package com.organization.EmployeeService.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class APIResponseDto {
	
	private EmployeeDto employeeDto;
	private DepartmentDto departmentDto;

}
