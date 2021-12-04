package com.projectdev.project.entities;

import java.util.Date;
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
public class Employee {
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long empId;
	@Column
	private String firstName;
	@Column
	private String lastName;
	@Column
	private String email;
	@Column
	private String phoneNumber;
	@Column
	private Date hireDate;
	@Column
	private double salary;
	@Column
	private double commisionPct;

	@OneToMany(mappedBy = "employee")
	private Set<JobHistory> jobHistories;

	@ManyToOne
	@JoinColumn(name = "jobId", nullable = false)
	private Job job;

	@ManyToOne
	@JoinColumn(name = "departmentId", nullable = false)
	private Departement departement;

	public long getEmpId() {
		return empId;
	}

	public void setEmpId(long empId) {
		this.empId = empId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Date getHireDate() {
		return hireDate;
	}

	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public double getCommisionPct() {
		return commisionPct;
	}

	public void setCommisionPct(double commisionPct) {
		this.commisionPct = commisionPct;
	}

	public Set<JobHistory> getJobHistories() {
		return jobHistories;
	}

	public void setJobHistories(Set<JobHistory> jobHistories) {
		this.jobHistories = jobHistories;
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

	public Employee(long empId, String firstName, String lastName, String email, String phoneNumber, Date hireDate,
			double salary, double commisionPct, Set<JobHistory> jobHistories, Job job, Departement departement) {
		super();
		this.empId = empId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.hireDate = hireDate;
		this.salary = salary;
		this.commisionPct = commisionPct;
		this.jobHistories = jobHistories;
		this.job = job;
		this.departement = departement;
	}

	public Employee() {

	}

	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", phoneNumber=" + phoneNumber + ", hireDate=" + hireDate + ", salary=" + salary + ", commisionPct="
				+ commisionPct + ", jobHistories=" + jobHistories + ", job=" + job + ", departement=" + departement
				+ "]";
	}

}
