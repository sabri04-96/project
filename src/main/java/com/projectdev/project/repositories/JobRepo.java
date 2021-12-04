
package com.projectdev.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projectdev.project.entities.Job;

public interface JobRepo extends JpaRepository<Job, Long> {

}