package com.capgemini.mbr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.mbr.model.ProjectStatus;
@Repository
public interface ProjectStatusRepository extends JpaRepository<ProjectStatus, Long> {

}
