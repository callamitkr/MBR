package com.capgemini.mbr.service;

import com.capgemini.mbr.model.Project;

import java.util.List;
import java.util.Optional;

public interface ProjectService {
    Optional<Project> findProject(Long projectId);
    Project createProject(Project project);
    void deleteProject(Long projectId);
    List<Project> getproject();
}
