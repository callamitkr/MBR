package com.capgemini.mbr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.mbr.model.Project;
@Repository
public interface ProjectRepository extends JpaRepository<Project, Long>{

}
