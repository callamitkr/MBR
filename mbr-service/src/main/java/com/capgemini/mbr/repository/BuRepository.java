package com.capgemini.mbr.repository;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import com.capgemini.mbr.model.Bu;
@Repository
public interface BuRepository extends JpaRepositoryImplementation<Bu,Long> {

}
