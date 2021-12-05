package com.projectdev.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projectdev.project.entities.Departement;

public interface DepartmetRepo extends JpaRepository<Departement, Long> {
	@SuppressWarnings("unchecked")
	Departement save(Departement departement);
}
