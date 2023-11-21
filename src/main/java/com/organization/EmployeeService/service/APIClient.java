package com.organization.EmployeeService.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.organization.EmployeeService.Dto.DepartmentDto;



@FeignClient(name= "DEPARTMENT-SERVICE")
public interface APIClient {
	
	@GetMapping("api/dept/{departmentCode}")
	DepartmentDto findDepartmentByCode(@PathVariable("departmentCode") String departmentCode);

}
