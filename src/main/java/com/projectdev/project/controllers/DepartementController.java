
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

import com.projectdev.project.entities.Departement;
import com.projectdev.project.repositories.DepartmetRepo;

@RestController
@RequestMapping("/project")
public class DepartementController {

	@Autowired
	DepartmetRepo repo;

	@GetMapping("/Departement")
	public ResponseEntity<List<Departement>> getAllDepartements() {
		try {
			List<Departement> departements = new ArrayList<Departement>();
			repo.findAll().forEach(departements::add);

			if (departements.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(departements, HttpStatus.FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/Departement/{id}")
	public ResponseEntity<Departement> getDepartementById(@PathVariable("id") long id) {
		Optional<Departement> departementDataOptional = repo.findById(id);
		if (departementDataOptional.isPresent()) {
			return new ResponseEntity<>(departementDataOptional.get(), HttpStatus.FOUND);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/Departement")
	public ResponseEntity<Departement> createDepartement(@RequestBody Departement departement) {
		try {
			Departement __dept = repo
					.save(new Departement(departement.getDepartmentId(), departement.getDepartementName(),
							departement.getLocation(), departement.getEmployees(), departement.getHistories()));

			return new ResponseEntity<>(__dept, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/Departement/{id}")
	public ResponseEntity<Departement> updateDepartement(@PathVariable("id") long id,
			@RequestBody Departement departementPassed) {
		Optional<Departement> deptData = repo.findById(id);
		if (deptData.isPresent()) {
			Departement departement = deptData.get();
			departement.setDepartementName(departementPassed.getDepartementName());
			departement.setEmployees(departementPassed.getEmployees());
			departement.setHistories(departementPassed.getHistories());
			departement.setLocation(departementPassed.getLocation());
			return new ResponseEntity<>(repo.save(departement), HttpStatus.OK);

		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/Departement/{id}")
	public ResponseEntity<HttpStatus> deleteDepartement(@PathVariable("id") long id) {
		try {
			repo.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/Departements")
	public ResponseEntity<HttpStatus> deleteAllDepartements() {
		try {
			repo.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}