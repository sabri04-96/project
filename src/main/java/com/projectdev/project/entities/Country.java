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
public class Country {

	@Column
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long countryId;
	@Column
	private String countryName;
	
	@ManyToOne
	@JoinColumn(name = "regionId", nullable = false)
	private Region region;
	
	
	@Column
	@OneToMany(mappedBy = "country")
	private Set<Location> locations;
	
	
	
	
	public Country(long countryId, String countryName, Region region, Set<Location> locations) {
		super();
		this.countryId = countryId;
		this.countryName = countryName;
		this.region = region;
		this.locations = locations;
	}
	
	
	
	
	public Country() {
		super();
	}




	public long getCountryId() {
		return countryId;
	}
	public void setCountryId(long countryId) {
		this.countryId = countryId;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	public Region getRegion() {
		return region;
	}
	public void setRegion(Region region) {
		this.region = region;
	}
	public Set<Location> getLocations() {
		return locations;
	}
	public void setLocations(Set<Location> locations) {
		this.locations = locations;
	}




	@Override
	public String toString() {
		return "Country [countryId=" + countryId + ", countryName=" + countryName + ", region=" + region
				+ ", locations=" + locations + "]";
	}

	
	
	
}
