package com.capgemini.mbr.controller;

import com.capgemini.mbr.bean.Response;
import com.capgemini.mbr.constant.MbrConstant;
import com.capgemini.mbr.exception.FoundException;
import com.capgemini.mbr.exception.NotFoundException;
import com.capgemini.mbr.model.ProjectStatus;
import com.capgemini.mbr.service.ProjectStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Locale;
import java.util.Optional;

public class ProjectStatusController {
    @Autowired
    ProjectStatusService projectStatusService;
    @Autowired
    MessageSource messageSource ;

    @PostMapping("/createProjectStatus")
    public ResponseEntity<Response> createProjectStatus(@Valid @RequestBody ProjectStatus projectStatus) throws FoundException {
        Optional<ProjectStatus> existingProjectStatus = projectStatusService.findProjectStatus(projectStatus.getStatusId());
        if (existingProjectStatus.isPresent()) {
            throw new FoundException(MbrConstant.PROJECT_STATUS_MESSAGE_KEY,messageSource.getMessage("projectStatus.exists",null, LocaleContextHolder.getLocale()));
        }
        ProjectStatus savedProjectStatus = projectStatusService.createProjectStatus(projectStatus);
        return new ResponseEntity(new Response(messageSource.getMessage("projectStatus.created",null, LocaleContextHolder.getLocale()), HttpStatus.CREATED),HttpStatus.CREATED);
    }
    @PutMapping("/updateProjectStatus")
    public ResponseEntity<Response> updateProjectStatus(@Valid @RequestBody ProjectStatus projectStatus) throws NotFoundException {
        Optional<ProjectStatus> existingProjectStatus = projectStatusService.findProjectStatus(projectStatus.getStatusId());
        if (! existingProjectStatus.isPresent()) {
            throw new NotFoundException(MbrConstant.PROJECT_STATUS_MESSAGE_KEY,messageSource.getMessage("projectStatus.notexists",null, Locale.getDefault()));
        }
        else{
            projectStatusService.createProjectStatus(projectStatus);
        }
        return new ResponseEntity(new Response(messageSource.getMessage("projectStatus.updated",null, Locale.getDefault()), HttpStatus.ACCEPTED),HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/deleteProjectStatus/{statusId}")
    public ResponseEntity<Response> deleteProjectStatus(@PathVariable Long statusId)
    {
        projectStatusService.deleteProjectStatus(statusId);
        return new ResponseEntity(new Response(messageSource.getMessage("projectStatus.deleted",null, Locale.getDefault()), HttpStatus.ACCEPTED),HttpStatus.ACCEPTED);
    }

}
