package com.projectdev.project.controllers.web;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.projectdev.project.repositories.JobRepo;
@Controller
public class JobWebController {

	private JobRepo JobRepo;

	public JobWebController(JobRepo JobRepo) {
		super();
		this.JobRepo = JobRepo;
	}

	@RequestMapping(value = "/web/jobs", method = RequestMethod.GET)
	public String listjob(Model model) {
		model.addAttribute("job", this.JobRepo.findAll());

		return "job/job";
	}

}