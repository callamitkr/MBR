package com.capgemini.mbr.controller;

import com.capgemini.mbr.bean.Response;
import com.capgemini.mbr.constant.MbrConstant;
import com.capgemini.mbr.exception.NotFoundException;
import com.capgemini.mbr.exception.FoundException;
import com.capgemini.mbr.model.Bu;
import com.capgemini.mbr.service.BuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Locale;
import java.util.Optional;

@RestController
public class BuController {
    @Autowired
    BuService buservice;
    @Autowired
    MessageSource messageSource ;


    @PostMapping("/createBu")
    public ResponseEntity<Response> createBu(@Valid @RequestBody Bu bu) throws FoundException {
        Optional<Bu> existingBu = buservice.findBu(bu.getBuId());
        if (existingBu.isPresent()) {
                throw new FoundException(MbrConstant.BU_MESSAGE_KEY,messageSource.getMessage("bu.exists",null,LocaleContextHolder.getLocale()));
        }
        Bu savedBu = buservice.createBu(bu);
        return new ResponseEntity(new Response(messageSource.getMessage("bu.created",null,Locale.getDefault()), HttpStatus.CREATED),HttpStatus.CREATED);
    }
    @PutMapping("/updateBu")
    public ResponseEntity<Response> updateBu(@Valid @RequestBody Bu bu) throws NotFoundException {
        Optional<Bu> existingBu = buservice.findBu(bu.getBuId());
        if (!existingBu.isPresent()) {
            throw new NotFoundException(MbrConstant.BU_MESSAGE_KEY,messageSource.getMessage("bu.notexists",null,Locale.getDefault()));
        }
        else{
            buservice.createBu(bu);
        }
        return new ResponseEntity(new Response(messageSource.getMessage("bu.updated",null,Locale.getDefault()), HttpStatus.ACCEPTED),HttpStatus.ACCEPTED);
    }

   @DeleteMapping("/deleteBu/{buId}")
   public ResponseEntity<Response> deleteBu(@PathVariable Long buId) {
       buservice.deleteBu(buId);
       return new ResponseEntity(new Response(messageSource.getMessage("bu.deleted",null,Locale.getDefault()), HttpStatus.ACCEPTED),HttpStatus.ACCEPTED);
   }


}
