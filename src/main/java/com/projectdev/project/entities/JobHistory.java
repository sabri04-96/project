
package com.projectdev.project.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class JobHistory {
	@Column
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long jobHistId;
	@Column
	private Date endDate;
	
	@ManyToOne
	@JoinColumn(name = "jobId", nullable = false)
	private Job job;
	
	@ManyToOne
	@JoinColumn(name = "departmentId", nullable = false)
	private Departement departement;
	
	@ManyToOne
	@JoinColumn(name = "empId", nullable = false)
	private Employee employee;

	public JobHistory(long jobHistId, Date endDate, Job job, Departement departement, Employee employee) {
		super();
		this.jobHistId = jobHistId;
		this.endDate = endDate;
		this.job = job;
		this.departement = departement;
		this.employee = employee;
	}

	public JobHistory() {
		super();
	}

	public long getJobHistId() {
		return jobHistId;
	}

	public void setJobHistId(long jobHistId) {
		this.jobHistId = jobHistId;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public Departement getDepartement() {
		return departement;
	}

	public void setDepartement(Departement departement) {
		this.departement = departement;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@Override
	public String toString() {
		return "JobHistory [jobHistId=" + jobHistId + ", endDate=" + endDate + ", job=" + job + ", departement="
				+ departement + ", employee=" + employee + "]";
	}
	
	
	
	
	
}
