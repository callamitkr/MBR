package com.capgemini.mbr.service;

import com.capgemini.mbr.model.ProjectStatus;
import com.capgemini.mbr.repository.ProjectStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ProjectStatusServiceImpl implements ProjectStatusService {

    @Autowired
    ProjectStatusRepository projectStatusRepository;
    @Override
    public Optional<ProjectStatus> findProjectStatus(Long statusId) {
        return projectStatusRepository.findById(statusId);
    }

    @Override
    public ProjectStatus createProjectStatus(ProjectStatus projectStatus) {
        return projectStatusRepository.save(projectStatus);
    }
    @Override
    public void deleteProjectStatus(Long ProjectStatus) {
        projectStatusRepository.deleteById(ProjectStatus);
    }

    @Override
    public List<ProjectStatus> getProjectStatus() {
        return projectStatusRepository.findAll();
    }
}
