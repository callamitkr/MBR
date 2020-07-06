package com.capgemini.mbr.service;

import com.capgemini.mbr.model.Bu;
import com.capgemini.mbr.repository.BuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class BuServiceImpl implements BuService {
    @Autowired
    BuRepository buRepository;
    @Override
    public Optional<Bu> findBu(Long buId) {
        return buRepository.findById(buId);
    }

    @Override
    public Bu createBu(Bu bu) {
        return buRepository.save(bu);
    }
    @Override
    public void deleteBu(Long buId) {
        buRepository.deleteById(buId);
    }

    @Override
    public List<Bu> getBu() {
        return buRepository.findAll();
    }
}
