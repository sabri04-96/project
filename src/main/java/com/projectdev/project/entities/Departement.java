package com.projectdev.project.entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Departement {

	@Column
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long departmentId;
	@Column
	private String departementName;

	@ManyToOne
	@JoinColumn(name = "locationId", nullable = false)
	private Location location;

	@OneToMany(mappedBy = "departement")
	private Set<Employee> employees;

	@OneToMany(mappedBy = "departement")
	private Set<JobHistory> histories;

	public Departement() {
		super();
	}
	
	

	public Departement(long departmentId, String departementName, Location location, Set<Employee> employees,
			Set<JobHistory> histories) {
		super();
		this.departmentId = departmentId;
		this.departementName = departementName;
		this.location = location;
		this.employees = employees;
		this.histories = histories;
	}



	public long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(long departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartementName() {
		return departementName;
	}

	public void setDepartementName(String departementName) {
		this.departementName = departementName;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Set<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}

	public Set<JobHistory> getHistories() {
		return histories;
	}

	public void setHistories(Set<JobHistory> histories) {
		this.histories = histories;
	}

}
