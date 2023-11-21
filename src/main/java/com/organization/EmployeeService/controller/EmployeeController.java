package com.organization.EmployeeService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.organization.EmployeeService.Dto.APIResponseDto;
import com.organization.EmployeeService.Dto.EmployeeDto;
import com.organization.EmployeeService.service.EmployeeService;

@RestController
@RequestMapping("api/emp")
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;
	
	@PostMapping(value = "/save")
	public ResponseEntity<EmployeeDto> saveEmp(@RequestBody EmployeeDto employeeDto){
		EmployeeDto employeeDto2 = employeeService.saveEmployee(employeeDto);
		return new ResponseEntity<EmployeeDto>(employeeDto2, HttpStatus.CREATED);
		
	}
	@GetMapping("{Id}")
	public ResponseEntity<APIResponseDto> getEmp(@PathVariable("Id") Long id){
		
		APIResponseDto dto = employeeService.getById(id);
		return new ResponseEntity<APIResponseDto>(dto, HttpStatus.OK);
		
	}

}
