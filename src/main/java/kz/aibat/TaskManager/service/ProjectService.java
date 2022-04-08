package kz.aibat.TaskManager.service;

import kz.aibat.TaskManager.model.Project;
import kz.aibat.TaskManager.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;

    public List<Project> getAllProjects(){
        return projectRepository.findAll();
    }

    public Project getProject(Long projectId){
        return projectRepository.findProjectById(projectId);
    }

    public Project addProject(Project project){
        return projectRepository.save(project);
    }
}
