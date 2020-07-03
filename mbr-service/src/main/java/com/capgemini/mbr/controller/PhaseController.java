package com.capgemini.mbr.controller;

import com.capgemini.mbr.bean.Response;
import com.capgemini.mbr.constant.MbrConstant;
import com.capgemini.mbr.exception.FoundException;
import com.capgemini.mbr.exception.NotFoundException;
import com.capgemini.mbr.model.Phase;
import com.capgemini.mbr.service.PhaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Locale;
import java.util.Optional;

public class PhaseController {
    @Autowired
    PhaseService phaseservice;
    @Autowired
    MessageSource messageSource ;
    
    @PostMapping("/createPhase")
    public ResponseEntity<Response> createPhase(@Valid @RequestBody Phase phase) throws FoundException {
        Optional<Phase> existingPhase = phaseservice.findPhase(phase.getPhaseId());
        if (existingPhase.isPresent()) {
            throw new FoundException(MbrConstant.PHASE_MESSAGE_KEY,messageSource.getMessage("Phase.exists",null, LocaleContextHolder.getLocale()));
        }
        Phase savedPhase = phaseservice.createPhase(phase);
        return new ResponseEntity(new Response("Phase Created", HttpStatus.CREATED),HttpStatus.CREATED);
    }
    @PutMapping("/updatePhase")
    public ResponseEntity<Response> updatePhase(@Valid @RequestBody Phase phase) throws NotFoundException {
        Optional<Phase> existingPhase = phaseservice.findPhase(phase.getPhaseId());
        if (!existingPhase.isPresent()) {
            throw new NotFoundException(MbrConstant.PHASE_MESSAGE_KEY,messageSource.getMessage("Phase.notexists",null, Locale.getDefault()));
        }
        else{
            phaseservice.createPhase(phase);
        }
        return new ResponseEntity(new Response(messageSource.getMessage("phase.updated",null,Locale.getDefault()), HttpStatus.ACCEPTED),HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/deletePhase/{phaseId}")
    public ResponseEntity<Response> deletePhase(@PathVariable Long phaseId)  {
        phaseservice.deletePhase(phaseId);
        return new ResponseEntity(new Response(messageSource.getMessage("phase.deleted",null,Locale.getDefault()), HttpStatus.ACCEPTED),HttpStatus.ACCEPTED);
    }


}
