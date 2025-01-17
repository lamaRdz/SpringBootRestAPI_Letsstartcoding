package com.letsstartcoding.springbootrestapiexample.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.letsstartcoding.springbootrestapiexample.dao.EmployeeDAO;
import com.letsstartcoding.springbootrestapiexample.model.Employee;

@RestController
@RequestMapping("/company")
public class EmployeeController {

	@Autowired
	EmployeeDAO employeeDAO;

	/* to save an employee */
	@PostMapping("/employees")
	public Employee createEmployee(@Valid @RequestBody Employee emp) {
		return employeeDAO.save(emp);
	}

	/* get all employees */
	@GetMapping("/employees")
	public List<Employee> getAllEmployees() {
		return employeeDAO.findAll();
	}

	/* get employee by empid */
	@GetMapping("/employees/{id}")
	public ResponseEntity<Optional<Employee>> getEmployeeById(@PathVariable(value = "id") Long empid) {
		Optional<Employee> emp = employeeDAO.findbyId(empid);

		if (emp == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok().body(emp);
	}

	/* update an employee by empid */
	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") Long empid,
			@Valid @RequestBody Employee empDetails) {
		
		Optional<Employee> emp = employeeDAO.findbyId(empid);

		if (emp == null) {
			return ResponseEntity.notFound().build();
		}

		emp.get().setName(empDetails.getName());
		emp.get().setDesignation(empDetails.getDesignation());
		emp.get().setExpertise(empDetails.getExpertise());
		emp.get().setName(empDetails.getName());

		Employee updateEmployee = employeeDAO.save(emp.get());
		return ResponseEntity.ok().body(updateEmployee);
	}

	/* delete employee */
	@DeleteMapping("/employees/{id}")
	public ResponseEntity<Employee> deleteEmployee(@PathVariable(value = "id") Long empid) {
		
		Optional<Employee> emp = employeeDAO.findbyId(empid);

		if (emp == null) {
			return ResponseEntity.notFound().build();
		}
		
		employeeDAO.delete(emp.get());
		return ResponseEntity.ok().build();

	}

}
