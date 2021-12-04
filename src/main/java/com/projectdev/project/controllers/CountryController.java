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

import com.projectdev.project.entities.Country;
import com.projectdev.project.repositories.CountryRepo;

@RestController
@RequestMapping("/project")
public class CountryController {

	@Autowired
	CountryRepo repo;

	@GetMapping("/Countries")
	public ResponseEntity<List<Country>> getAllCountries() {
		try {
			List<Country> contCountries = new ArrayList<Country>();
			repo.findAll().forEach(contCountries::add);

			if (contCountries.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(contCountries, HttpStatus.FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/Countries/{id}")
	public ResponseEntity<Country> getCountryById(@PathVariable("id") long id) {
		Optional<Country> countryDataOptional = repo.findById(id);
		if (countryDataOptional.isPresent()) {
			return new ResponseEntity<>(countryDataOptional.get(), HttpStatus.FOUND);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/Countries")
	public ResponseEntity<Country> createCountry(@RequestBody Country country) {
		try {
			Country _country = repo.save(new Country(country.getCountryId(), country.getCountryName(),
					country.getRegion(), country.getLocations()));

			return new ResponseEntity<>(_country, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/Countries/{id}")
	public ResponseEntity<Country> updateCountry(@PathVariable("id") long id, @RequestBody Country countryPassed) {
		Optional<Country> countryData = repo.findById(id);
		if (countryData.isPresent()) {
			Country country = countryData.get();
			country.setCountryName(countryPassed.getCountryName());
			country.setLocations(countryPassed.getLocations());
			country.setRegion(countryPassed.getRegion());
			return new ResponseEntity<>(repo.save(country), HttpStatus.OK);

		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/Countries/{id}")
	public ResponseEntity<HttpStatus> deleteCountry(@PathVariable("id") long id) {
		try {
			repo.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/Countries")
	public ResponseEntity<HttpStatus> deleteAllCountries() {
		try {
			repo.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}