package com.capgemini.mbr.service;

import com.capgemini.mbr.model.Phase;

import java.util.Optional;

public interface PhaseService {
    Optional<Phase> findPhase(Long phaseId);
    Phase createPhase(Phase phase);
    void deletePhase(Long phaseId);

}
