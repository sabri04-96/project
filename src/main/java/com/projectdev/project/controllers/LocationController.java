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

import com.projectdev.project.entities.Location;
import com.projectdev.project.repositories.LocationRepo;

@RestController
@RequestMapping("/project")
public class LocationController {

	@Autowired
	LocationRepo repo;

	@GetMapping("/Locations")
	public ResponseEntity<List<Location>> getAllLocations() {
		try {
			List<Location> locations = new ArrayList<Location>();
			repo.findAll().forEach(locations::add);

			if (locations.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(locations, HttpStatus.FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/Location/{id}")
	public ResponseEntity<Location> getLocationById(@PathVariable("id") long id) {
		Optional<Location> LocationDataOptional = repo.findById(id);
		if (LocationDataOptional.isPresent()) {
			return new ResponseEntity<>(LocationDataOptional.get(), HttpStatus.FOUND);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/Location")
	public ResponseEntity<Location> createLocation(@RequestBody Location location) {
		try {
			Location _Location = repo.save(new Location(location.getLocationId(), location.getStreetAdress(), location.getPostalCode(), location.getCity(), location.getStateProvince(), location.getCountry(), location.getDepartments()));

			return new ResponseEntity<>(_Location, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/Location/{id}")
	public ResponseEntity<Location> updateLocation(@PathVariable("id") long id, @RequestBody Location locationPassed) {
		Optional<Location> locationData = repo.findById(id);
		if (locationData.isPresent()) {
			Location location = locationData.get();
			location.setCity(locationPassed.getCity());
			location.setCountry(locationPassed.getCountry());
			location.setDepartments(locationPassed.getDepartments());
			location.setPostalCode(locationPassed.getPostalCode());
			location.setStateProvince(locationPassed.getStateProvince());
			location.setStreetAdress(locationPassed.getStreetAdress());

			return new ResponseEntity<>(repo.save(location), HttpStatus.OK);

		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/Location/{id}")
	public ResponseEntity<HttpStatus> deleteLocation(@PathVariable("id") long id) {
		try {
			repo.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/Locations")
	public ResponseEntity<HttpStatus> deleteAllLocations() {
		try {
			repo.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}