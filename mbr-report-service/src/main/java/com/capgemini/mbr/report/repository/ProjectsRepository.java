package com.capgemini.mbr.report.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.mbr.report.model.Projects;



@Repository
public interface ProjectsRepository extends JpaRepository<Projects, Long>{

}
