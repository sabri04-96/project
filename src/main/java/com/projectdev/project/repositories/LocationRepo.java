package com.projectdev.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projectdev.project.entities.Location;

public interface LocationRepo extends JpaRepository<Location, Long> {

}