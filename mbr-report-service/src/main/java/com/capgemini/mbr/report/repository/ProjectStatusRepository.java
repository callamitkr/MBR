package com.capgemini.mbr.report.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.mbr.report.model.ProjectStatus;



@Repository
public interface ProjectStatusRepository extends JpaRepository<ProjectStatus, Long> {

}
