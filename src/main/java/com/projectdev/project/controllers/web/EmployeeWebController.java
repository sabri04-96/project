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
	public String listEmployee(Model model) {
		model.addAttribute("employees", this.empRepo.findAll());

		return "employee/employee";
	}
	/*
	 * @RequestMapping(value = "/web/add-employee") public String
	 * addEmployee(@ModelAttribute("employee") final Employee employee, final Model
	 * model) { this.empRepo.save(employee); model.addAttribute("msg",
	 * "This employee has been created successfully");
	 * model.addAttribute("msgColour", "success");
	 * 
	 * return "employee/employees-add"; }
	 * 
	 * @GetMapping(value = { "/employee-delete", "/delete" }) public String
	 * handleAdminEmployeesDelete(@RequestParam("employeeId") final long employeeId)
	 * {
	 * 
	 * this.empRepo.deleteById(employeeId); return "redirect:/employee/employee"; }
	 * 
	 * @PostMapping(value = {"/admin-employees-edit"}) public String
	 * handleAdminEmployeesEdit(@ModelAttribute("employee") final Employee employee,
	 * final BindingResult error, final Model model) { return
	 * "admins/employees/admin-employees-edit"; }
	 */
}