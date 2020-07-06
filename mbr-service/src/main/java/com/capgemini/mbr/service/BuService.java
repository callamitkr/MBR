package com.capgemini.mbr.service;

import com.capgemini.mbr.model.Bu;

import java.util.List;
import java.util.Optional;

public interface BuService {

    Optional<Bu> findBu(Long buId);
    Bu createBu(Bu bu);
    void deleteBu(Long buId);
    List<Bu> getBu();
}
