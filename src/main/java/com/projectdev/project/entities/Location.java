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
public class Location {

	@Column
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long locationId;
	@Column
	private String streetAdress;
	@Column
	private String postalCode;
	@Column
	private String city;
	@Column
	private String stateProvince;
	@ManyToOne
	@JoinColumn(name = "countryId", nullable = false)
	private Country country;
	@OneToMany(mappedBy = "location")
	private Set<Departement> departments;

	public Location(long locationId, String streetAdress, String postalCode, String city, String stateProvince,
			Country country, Set<Departement> departments) {
		super();
		this.locationId = locationId;
		this.streetAdress = streetAdress;
		this.postalCode = postalCode;
		this.city = city;
		this.stateProvince = stateProvince;
		this.country = country;
		this.departments = departments;
	}

	public Location() {
		super();
	}

	public long getLocationId() {
		return locationId;
	}

	public void setLocationId(long locationId) {
		this.locationId = locationId;
	}

	public String getStreetAdress() {
		return streetAdress;
	}

	public void setStreetAdress(String streetAdress) {
		this.streetAdress = streetAdress;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStateProvince() {
		return stateProvince;
	}

	public void setStateProvince(String stateProvince) {
		this.stateProvince = stateProvince;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public Set<Departement> getDepartments() {
		return departments;
	}

	public void setDepartments(Set<Departement> departments) {
		this.departments = departments;
	}

	@Override
	public String toString() {
		return "Location [locationId=" + locationId + ", streetAdress=" + streetAdress + ", postalCode=" + postalCode
				+ ", city=" + city + ", stateProvince=" + stateProvince + ", country=" + country + ", departments="
				+ departments + "]";
	}

}