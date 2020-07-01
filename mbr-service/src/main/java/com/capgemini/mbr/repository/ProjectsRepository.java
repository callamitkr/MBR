package com.capgemini.mbr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.mbr.model.Projects;
@Repository
public interface ProjectsRepository extends JpaRepository<Projects, Long>{

}
