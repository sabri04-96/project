package com.projectdev.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projectdev.project.entities.Country;

public interface CountryRepo extends JpaRepository<Country, Long> {

}
