
package com.projectdev.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projectdev.project.entities.JobHistory;

public interface JobHistoryRepo extends JpaRepository<JobHistory, Long> {

}