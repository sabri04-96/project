package com.projectdev.project.entities;



import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Job {

	@Column
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long jobId;
	@Column
	private String jobTitle;
	@Column
	private double minSalary;
	@Column
	private double maxSalary;
	
	
	@OneToMany(mappedBy = "job")
	private Set<Employee> employees;
	
	@OneToMany(mappedBy = "job")
	private Set<JobHistory> JobHistories;

	public Job(long jobId, String jobTitle, double minSalary, double maxSalary, Set<Employee> employees,
			Set<JobHistory> jobHistories) {
		super();
		this.jobId = jobId;
		this.jobTitle = jobTitle;
		this.minSalary = minSalary;
		this.maxSalary = maxSalary;
		this.employees = employees;
		JobHistories = jobHistories;
	}

	public Job() {
		super();
	}

	public long getJobId() {
		return jobId;
	}

	public void setJobId(long jobId) {
		this.jobId = jobId;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public double getMinSalary() {
		return minSalary;
	}

	public void setMinSalary(double minSalary) {
		this.minSalary = minSalary;
	}

	public double getMaxSalary() {
		return maxSalary;
	}

	public void setMaxSalary(double maxSalary) {
		this.maxSalary = maxSalary;
	}

	public Set<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}

	public Set<JobHistory> getJobHistories() {
		return JobHistories;
	}

	public void setJobHistories(Set<JobHistory> jobHistories) {
		JobHistories = jobHistories;
	}
	
	
	
	
	
	
}