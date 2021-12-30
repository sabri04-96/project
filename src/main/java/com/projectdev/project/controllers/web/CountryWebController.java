package com.projectdev.project.controllers.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.projectdev.project.repositories.CountryRepo;


@Controller
public class CountryWebController {

	private CountryRepo countryRepo;

	public CountryWebController(CountryRepo country) {
		super();
		this.countryRepo = country;
	}

	@RequestMapping(value = "/web/countries", method = RequestMethod.GET)
	public String listEmployee(Model model) {
		model.addAttribute("country", this.countryRepo.findAll());

		return "country/country";
	}
}