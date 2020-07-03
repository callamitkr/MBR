package com.capgemini.mbr.service;

import com.capgemini.mbr.model.Phase;
import com.capgemini.mbr.repository.PhaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class PhaseServiceImpl implements PhaseService {
    @Autowired
    PhaseRepository phaseRepository;

    @Override
    public Optional<Phase> findPhase(Long phaseId) {
        return phaseRepository.findById(phaseId);
    }
    @Override
    public Phase createPhase(Phase phase) {
        return phaseRepository.save(phase);
    }
    @Override
    public void deletePhase(Long phaseId) {
        phaseRepository.deleteById(phaseId);
    }
}
