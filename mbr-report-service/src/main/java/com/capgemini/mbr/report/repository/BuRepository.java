package com.capgemini.mbr.report.repository;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import com.capgemini.mbr.report.model.Bu;


@Repository
public interface BuRepository extends JpaRepositoryImplementation<Bu,Long> {

}
