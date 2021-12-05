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

import com.projectdev.project.entities.Job;
import com.projectdev.project.repositories.JobRepo;

@RestController
@RequestMapping("/project")
public class JobController {

	@Autowired
	JobRepo repo;

	@GetMapping("/Jobs")
	public ResponseEntity<List<Job>> getAllJobs() {
		try {
			List<Job> jobs = new ArrayList<Job>();
			repo.findAll().forEach(jobs::add);

			if (jobs.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(jobs, HttpStatus.FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/Job/{id}")
	public ResponseEntity<Job> getJobById(@PathVariable("id") long id) {
		Optional<Job> JobDataOptional = repo.findById(id);
		if (JobDataOptional.isPresent()) {
			return new ResponseEntity<>(JobDataOptional.get(), HttpStatus.FOUND);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/Job")
	public ResponseEntity<Job> createJob(@RequestBody Job job) {
		try {
			Job _Job = repo.save(new Job(job.getJobId(), job.getJobTitle(), job.getMinSalary(), job.getMaxSalary(),
					job.getEmployees(), job.getJobHistories()));

			return new ResponseEntity<>(_Job, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/Job/{id}")
	public ResponseEntity<Job> updateJob(@PathVariable("id") long id, @RequestBody Job jobPassed) {
		Optional<Job> jobData = repo.findById(id);
		if (jobData.isPresent()) {
			Job job = jobData.get();
			job.setJobTitle(jobPassed.getJobTitle());
			job.setMaxSalary(jobPassed.getMaxSalary());
			job.setMinSalary(jobPassed.getMinSalary());
			job.setEmployees(jobPassed.getEmployees());
			job.setJobHistories(jobPassed.getJobHistories());
			return new ResponseEntity<>(repo.save(job), HttpStatus.OK);

		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/Job/{id}")
	public ResponseEntity<HttpStatus> deleteJob(@PathVariable("id") long id) {
		try {
			repo.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/Jobs")
	public ResponseEntity<HttpStatus> deleteAllJobs() {
		try {
			repo.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}