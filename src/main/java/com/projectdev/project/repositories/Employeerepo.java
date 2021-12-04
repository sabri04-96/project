package com.projectdev.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projectdev.project.entities.Employee;

public interface Employeerepo extends JpaRepository<Employee, Long> {

	@SuppressWarnings("unchecked")
	Employee save(Employee employee);

}