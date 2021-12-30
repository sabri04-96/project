package com.projectdev.project.controllers.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.projectdev.project.repositories.DepartmetRepo;

@Controller
public class DepartementWebController {

	private DepartmetRepo deptRepo;

	public DepartementWebController(DepartmetRepo deptRepo) {
		super();
		this.deptRepo = deptRepo;
	}

	@RequestMapping(value = "/web/departmenets", method = RequestMethod.GET)
	public String listEmployee(Model model) {
		model.addAttribute("depts", this.deptRepo.findAll());

		return "departments/departements";
	}
}

