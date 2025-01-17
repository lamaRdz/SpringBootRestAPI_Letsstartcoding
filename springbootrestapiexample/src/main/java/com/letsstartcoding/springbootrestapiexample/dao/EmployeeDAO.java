package com.letsstartcoding.springbootrestapiexample.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.letsstartcoding.springbootrestapiexample.model.Employee;
import com.letsstartcoding.springbootrestapiexample.repository.EmployeeRepository;

@Service
public class EmployeeDAO {
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	/* to save an employee*/
	public Employee save(Employee emp) {
		return employeeRepository.save(emp);
	}
	
	/* search all employees*/
	public List<Employee> findAll(){
		return employeeRepository.findAll();
	}
	
	/* get an employee*/
	public Optional<Employee> findbyId(Long empid){
		return employeeRepository.findById(empid);
	}
	
	/* delete an employee*/
	public void delete(Employee emp) {
		employeeRepository.delete(emp);
	}

}
