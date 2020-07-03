package com.capgemini.mbr.service;

import com.capgemini.mbr.model.ProjectStatus;

import java.util.Optional;

public interface ProjectStatusService {
    Optional<ProjectStatus> findProjectStatus(Long statusId);
    ProjectStatus createProjectStatus(ProjectStatus ProjectStatus);
    void deleteProjectStatus(Long statusId);
}
