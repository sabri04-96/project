package com.projectdev.project.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projectdev.project.entities.Employee;
import com.projectdev.project.repositories.Employeerepo;

@RestController
@RequestMapping("/project")
public class EmployeeController {

	@Autowired
	Employeerepo repo;

	@GetMapping("/Employees")
	public ResponseEntity<List<Employee>> getAllEmployees() {
		try {
			List<Employee> employees = new ArrayList<Employee>();
			repo.findAll().forEach(employees::add);

			if (employees.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(employees, HttpStatus.FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/Employee/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") long id) {
		Optional<Employee> employeeDataOptional = repo.findById(id);
		if (employeeDataOptional.isPresent()) {
			return new ResponseEntity<>(employeeDataOptional.get(), HttpStatus.FOUND);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/Employees")
	public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
		try {
			Employee _employer = repo.save(new Employee(employee.getEmpId(), employee.getFirstName(),
					employee.getLastName(), employee.getEmail(), employee.getPhoneNumber(), employee.getHireDate(),
					employee.getSalary(), employee.getCommisionPct(), employee.getJobHistories(), employee.getJob(),
					employee.getDepartement()));

			return new ResponseEntity<>(_employer, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/Employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable("id") long id, @RequestBody Employee employeePassed) {
		Optional<Employee> empData = repo.findById(id);
		if (empData.isPresent()) {
			Employee employer = empData.get();
			employer.setFirstName(employeePassed.getFirstName());
			employer.setEmail(employeePassed.getEmail());
			employer.setHireDate(employeePassed.getHireDate());
			employer.setCommisionPct(employeePassed.getCommisionPct());
			employer.setDepartement(employeePassed.getDepartement());
			employer.setJob(employeePassed.getJob());
			employer.setLastName(employeePassed.getLastName());
			employer.setPhoneNumber(employeePassed.getPhoneNumber());
			employer.setSalary(employeePassed.getSalary());
			employer.setJobHistories(employeePassed.getJobHistories());
			return new ResponseEntity<>(repo.save(employer), HttpStatus.OK);

		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/Employees/{id}")
	public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable("id") long id) {
		try {
			repo.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}   

	@DeleteMapping("/Employees")
	public ResponseEntity<HttpStatus> deleteAllEmployees() {
		try {
			repo.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}