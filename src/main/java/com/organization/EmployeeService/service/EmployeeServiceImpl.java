package com.organization.EmployeeService.service;

import java.net.URI;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.organization.EmployeeService.Dto.APIResponseDto;
import com.organization.EmployeeService.Dto.DepartmentDto;
import com.organization.EmployeeService.Dto.EmployeeDto;
import com.organization.EmployeeService.Entity.Employee;
import com.organization.EmployeeService.Repositary.EmployeeRepositary;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService{
	
	//@Autowired
	private EmployeeRepositary employeeRepositary;
	
	//@Autowired
//	private RestTemplate restTemplate;
	
	private WebClient webClient;
	
//	private APIClient apiClient;

	@Override
	public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
		Employee employee = new Employee(
				employeeDto.getId(),
				employeeDto.getFirstName(),
				employeeDto.getLastName(),
				employeeDto.getEmail(),
				employeeDto.getDepartmentCode()
				);
		Employee savedEmployee = employeeRepositary.save(employee);
		EmployeeDto savedEmployeeDto = new EmployeeDto(
				savedEmployee.getId(),
				savedEmployee.getFirstName(),
				savedEmployee.getLastName(),
				savedEmployee.getEmail(),
				savedEmployee.getDepartmentCode()
				);
		return savedEmployeeDto;
	}

	@Retry(name = "${spring.application.name}", fallbackMethod = "getDefaultDepartment")
	//@CircuitBreaker(name = "${spring.application.name}", fallbackMethod = "getDefaultDepartment")
	@Override
	public APIResponseDto getById(Long id) {
		Employee employee = employeeRepositary.findById(id).get();
		
		//ResponseEntity<DepartmentDto> responseEntity = restTemplate.getForEntity("http://localhost:8080/api/dept/" + employee.getDepartmentCode(), DepartmentDto.class);
		//DepartmentDto departmentDto = responseEntity.getBody();
		
		DepartmentDto departmentDto = webClient.get()
				 .uri("http://localhost:8080/api/dept/" + employee.getDepartmentCode())
				 .retrieve()
				 .bodyToMono(DepartmentDto.class)
				 .block();

//		DepartmentDto departmentDto = apiClient.findDepartmentByCode(employee.getDepartmentCode());
		
		EmployeeDto employeeDto = new EmployeeDto(
				employee.getId(),
				employee.getFirstName(),
				employee.getLastName(),
				employee.getEmail(),
				employee.getDepartmentCode()
				);
		APIResponseDto apiResponseDto = new APIResponseDto();
		apiResponseDto.setDepartmentDto(departmentDto);
		apiResponseDto.setEmployeeDto(employeeDto);
		return apiResponseDto;
	}
	
	public APIResponseDto getDefaultDepartment(Long id, Exception exception) {
		
		Employee employee = employeeRepositary.findById(id).get();
		
		DepartmentDto departmentDto=new DepartmentDto();
		departmentDto.setDepartmentName("R&D Department");
		departmentDto.setDepartmentCode("RD001");
		departmentDto.setDepartmentDescription("Research and Development");
		
		EmployeeDto employeeDto = new EmployeeDto(
				employee.getId(),
				employee.getFirstName(),
				employee.getLastName(),
				employee.getEmail(),
				employee.getDepartmentCode()
				);
		APIResponseDto apiResponseDto = new APIResponseDto();
		apiResponseDto.setDepartmentDto(departmentDto);
		apiResponseDto.setEmployeeDto(employeeDto);
		return apiResponseDto;
		
	}

}
