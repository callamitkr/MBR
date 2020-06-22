package com.capgemini.mbr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.capgemini.mbr.model.Report;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReportRepository  extends JpaRepository<Report, Long> {
	
	@Query("select r from Report r where month(r.createdDate) = ?1 and year(r.createdDate) = ?2")
	List<Report> getReportsByMonthYear(int month,int year);
	
	@Query("select r from Report r where month(r.createdDate) = month(now()) and createdBy  = ?1")
	Optional<Report> findReportOfCurrentMonthByCreatedBy(String userId);

}


