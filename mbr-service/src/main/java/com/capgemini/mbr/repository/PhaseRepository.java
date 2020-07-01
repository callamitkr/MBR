package com.capgemini.mbr.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.mbr.model.Phase;

@Repository
public interface PhaseRepository extends JpaRepository<Phase, Long> {

}
