package com.capgemini.mbr.service;

import com.capgemini.mbr.model.Project;
import com.capgemini.mbr.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
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
    @Override
    public List<Project> getproject() {
        return projectRepository.findAll();
    }
}
