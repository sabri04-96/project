package com.projectdev.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projectdev.project.entities.Region;

public interface RegionRepo extends JpaRepository<Region, Long> {

}