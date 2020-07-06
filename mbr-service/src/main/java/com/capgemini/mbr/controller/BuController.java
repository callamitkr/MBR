package com.capgemini.mbr.controller;

import com.capgemini.mbr.bean.Response;
import com.capgemini.mbr.exception.DataNotFoundException;
import com.capgemini.mbr.exception.DataFoundException;
import com.capgemini.mbr.model.Bu;
import com.capgemini.mbr.service.BuService;
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
public class BuController {
    @Autowired
    private BuService buService;
    @Autowired
    private MessageSource messageSource ;


    @PostMapping("/createBu")
    public ResponseEntity<Response> createBu(@Valid @RequestBody Bu bu) throws DataFoundException {
        Optional<Bu> existingBu = buService.findBu(bu.getBuId());
        if (existingBu.isPresent()) {
                throw new DataFoundException(messageSource.getMessage("bu.exists",null,LocaleContextHolder.getLocale()));
        }
        Bu savedBu = buService.createBu(bu);
        return new ResponseEntity(new Response(messageSource.getMessage("bu.created",null,Locale.getDefault()), HttpStatus.CREATED),HttpStatus.CREATED);
    }
    @PutMapping("/updateBu")
    public ResponseEntity<Response> updateBu(@Valid @RequestBody Bu bu) throws DataNotFoundException {
        Optional<Bu> existingBu = buService.findBu(bu.getBuId());
        if (!existingBu.isPresent()) {
            throw new DataNotFoundException(messageSource.getMessage("bu.notexists",null,Locale.getDefault()));
        }
        else{
            buService.createBu(bu);
        }
        return new ResponseEntity(new Response(messageSource.getMessage("bu.updated",null,Locale.getDefault()), HttpStatus.ACCEPTED),HttpStatus.ACCEPTED);
    }

   @DeleteMapping("/deleteBu/{buId}")
   public ResponseEntity<Response> deleteBu(@PathVariable Long buId) {
       buService.deleteBu(buId);
       return new ResponseEntity(new Response(messageSource.getMessage("bu.deleted",null,Locale.getDefault()), HttpStatus.ACCEPTED),HttpStatus.ACCEPTED);
   }
    @GetMapping("/getBu")
    public ResponseEntity<List<Bu>> getBu() throws DataNotFoundException {
        List<Bu> bus  = buService.getBu();
        if(bus.size() == 0) {
            throw new DataNotFoundException(messageSource.getMessage("bu.notfound", null, Locale.getDefault()));
        }
        return ResponseEntity.ok().body(bus);
    }


}
