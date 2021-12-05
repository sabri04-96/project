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

import com.projectdev.project.entities.Region;
import com.projectdev.project.repositories.RegionRepo;

@RestController
@RequestMapping("/project")
public class RegionController {

	@Autowired
	RegionRepo repo;

	@GetMapping("/Regions")
	public ResponseEntity<List<Region>> getAllRegions() {
		try {
			List<Region> regions = new ArrayList<Region>();
			repo.findAll().forEach(regions::add);

			if (regions.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(regions, HttpStatus.FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/Region/{id}")
	public ResponseEntity<Region> getRegionById(@PathVariable("id") long id) {
		Optional<Region> RegionDataOptional = repo.findById(id);
		if (RegionDataOptional.isPresent()) {
			return new ResponseEntity<>(RegionDataOptional.get(), HttpStatus.FOUND);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/Region")
	public ResponseEntity<Region> createRegion(@RequestBody Region region) {
		try {
			Region _Region = repo.save(new Region(region.getRegionId(), region.getRegionName(), region.getCountries()));

			return new ResponseEntity<>(_Region, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/Region/{id}")
	public ResponseEntity<Region> updateRegion(@PathVariable("id") long id, @RequestBody Region regionPassed) {
		Optional<Region> regionData = repo.findById(id);
		if (regionData.isPresent()) {
			Region region = regionData.get();
			region.setRegionName(regionPassed.getRegionName());
			region.setCountries(regionPassed.getCountries());

			return new ResponseEntity<>(repo.save(region), HttpStatus.OK);

		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/Region/{id}")
	public ResponseEntity<HttpStatus> deleteRegion(@PathVariable("id") long id) {
		try {
			repo.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/Regions")
	public ResponseEntity<HttpStatus> deleteAllRegions() {
		try {
			repo.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}