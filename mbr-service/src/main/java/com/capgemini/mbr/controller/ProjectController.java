package com.capgemini.mbr.controller;

import com.capgemini.mbr.bean.Response;
import com.capgemini.mbr.constant.MbrConstant;
import com.capgemini.mbr.exception.FoundException;
import com.capgemini.mbr.exception.NotFoundException;
import com.capgemini.mbr.model.Project;
import com.capgemini.mbr.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Locale;
import java.util.Optional;

public class ProjectController {
    @Autowired
    ProjectService projectService;
    @Autowired
    MessageSource messageSource ;

    @PostMapping("/createProject")

    public ResponseEntity<Response> createProject(@Valid @RequestBody Project project) throws FoundException {
        Optional<Project> existingProject = projectService.findProject(project.getProjectId());
        if (existingProject.isPresent()) {
            throw new FoundException(MbrConstant.PROJECT_MESSAGE_KEY,messageSource.getMessage("project.exists",null, LocaleContextHolder.getLocale()));
        }
        Project savedProject = projectService.createProject(project);
        return new ResponseEntity(new Response(messageSource.getMessage("project.created",null,Locale.getDefault()), HttpStatus.CREATED),HttpStatus.CREATED);
    }
    @PutMapping("/updateProject")
    public ResponseEntity<Response> updateProject(@Valid @RequestBody Project project) throws NotFoundException {
        Optional<Project> existingBu = projectService.findProject(project.getProjectId());
        if (!existingBu.isPresent()) {
            throw new NotFoundException(MbrConstant.PROJECT_MESSAGE_KEY,messageSource.getMessage("project.notexists",null, Locale.getDefault()));
        }
        else{
            projectService.createProject(project);
        }
        return new ResponseEntity(new Response(messageSource.getMessage("project.updated",null, Locale.getDefault()), HttpStatus.ACCEPTED),HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/deleteProject/{buId}")
    public ResponseEntity<Response> deleteProject(@PathVariable Long projectId) {
        projectService.deleteProject(projectId);
        return new ResponseEntity(new Response(messageSource.getMessage("project.deleted",null, Locale.getDefault()), HttpStatus.ACCEPTED),HttpStatus.ACCEPTED);
    }

}
