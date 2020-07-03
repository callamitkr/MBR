package com.capgemini.mbr.service;

import com.capgemini.mbr.model.Project;
import com.capgemini.mbr.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class ProjectServiceImpl implements ProjectService {
    @Autowired
    ProjectRepository projectRepository;
    @Override
    public Optional<Project> findProject(Long projectId) {
        return projectRepository.findById(projectId);
    }
    @Override
    public Project createProject(Project project) {
        return projectRepository.save(project);
    }
    @Override
    public void deleteProject(Long projectId) {
        projectRepository.deleteById(projectId);
    }
}
