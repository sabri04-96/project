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

import com.projectdev.project.entities.JobHistory;
import com.projectdev.project.repositories.JobHistoryRepo;

@RestController
@RequestMapping("/project")
public class JobHistoryController {

	@Autowired
	JobHistoryRepo repo;

	@GetMapping("/JobHistories")
	public ResponseEntity<List<JobHistory>> getAllJobHistories() {
		try {
			List<JobHistory> hists = new ArrayList<JobHistory>();
			repo.findAll().forEach(hists::add);

			if (hists.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(hists, HttpStatus.FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/JobHistory/{id}")
	public ResponseEntity<JobHistory> getJobHistoriesById(@PathVariable("id") long id) {
		Optional<JobHistory> HistsDataOptional = repo.findById(id);
		if (HistsDataOptional.isPresent()) {
			return new ResponseEntity<>(HistsDataOptional.get(), HttpStatus.FOUND);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/JobHistory")
	public ResponseEntity<JobHistory> createJobHistory(@RequestBody JobHistory jobHistory) {
		try {
			JobHistory _Hist = repo.save(new JobHistory(jobHistory.getJobHistId(), jobHistory.getEndDate(),
					jobHistory.getJob(), jobHistory.getDepartement(), jobHistory.getEmployee()));

			return new ResponseEntity<>(_Hist, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/JobHistory/{id}")
	public ResponseEntity<JobHistory> updateJobHistory(@PathVariable("id") long id,
			@RequestBody JobHistory historyPassed) {
		Optional<JobHistory> historyData = repo.findById(id);
		if (historyData.isPresent()) {
			JobHistory history = historyData.get();
			history.setDepartement(historyPassed.getDepartement());
			history.setEndDate(historyPassed.getEndDate());
			history.setJob(historyPassed.getJob());
			history.setEmployee(history.getEmployee());

			return new ResponseEntity<>(repo.save(history), HttpStatus.OK);

		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/JobHistory/{id}")
	public ResponseEntity<HttpStatus> deleteJobHistory(@PathVariable("id") long id) {
		try {
			repo.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/JobHistories")
	public ResponseEntity<HttpStatus> deleteAllHistories() {
		try {
			repo.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}