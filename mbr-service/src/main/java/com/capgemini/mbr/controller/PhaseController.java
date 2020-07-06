package com.capgemini.mbr.controller;

import com.capgemini.mbr.bean.Response;
import com.capgemini.mbr.constant.MbrConstant;
import com.capgemini.mbr.exception.DataFoundException;
import com.capgemini.mbr.exception.DataNotFoundException;
import com.capgemini.mbr.model.Phase;
import com.capgemini.mbr.service.PhaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
@RestController
public class PhaseController {
    @Autowired
    PhaseService phaseservice;
    @Autowired
    MessageSource messageSource ;
    
    @PostMapping("/createPhase")
    public ResponseEntity<Response> createPhase(@Valid @RequestBody Phase phase) throws DataFoundException {
        Optional<Phase> existingPhase = phaseservice.findPhase(phase.getPhaseId());
        if (existingPhase.isPresent()) {
            throw new DataFoundException(messageSource.getMessage("phase.exists",null, LocaleContextHolder.getLocale()));
        }
        Phase savedPhase = phaseservice.createPhase(phase);
        return new ResponseEntity(new Response("Phase Created", HttpStatus.CREATED),HttpStatus.CREATED);
    }
    @PutMapping("/updatePhase")
    public ResponseEntity<Response> updatePhase(@Valid @RequestBody Phase phase) throws DataNotFoundException {
        Optional<Phase> existingPhase = phaseservice.findPhase(phase.getPhaseId());
        if (!existingPhase.isPresent()) {
            throw new DataNotFoundException(messageSource.getMessage("phase.notexists",null, Locale.getDefault()));
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

    @GetMapping("/getPhase")
    public ResponseEntity<List<Phase>> getPhase() throws DataNotFoundException {
        List<Phase> phases  = phaseservice.getPhase();
        if(phases.size() == 0) {
            throw new DataNotFoundException(messageSource.getMessage("phase.notfound", null, Locale.getDefault()));
        }
        return ResponseEntity.ok().body(phases);
    }

}
