package com.projectdev.project.controllers.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.projectdev.project.repositories.Employeerepo;

@Controller
public class EmployeeWebController {

	private Employeerepo empRepo;

	public EmployeeWebController(Employeerepo empRepo) {
		super();
		this.empRepo = empRepo;
	}



	@RequestMapping(value = "/web/employees", method = RequestMethod.GET)
	public String pageEmployee(Model model) {
		model.addAttribute("employees", this.empRepo.findAll());

		return "employee";
	}

}
