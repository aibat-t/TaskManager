package kz.aibat.TaskManager.service;

import kz.aibat.TaskManager.dto.ProjectDTO;
import kz.aibat.TaskManager.mapper.ProjectMapper;
import kz.aibat.TaskManager.model.Project;
import kz.aibat.TaskManager.repository.ProjectRepository;
import kz.aibat.TaskManager.repository.ThemeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final ThemeRepository themeRepository;
    private final ProjectMapper projectMapper;

    public List<Project> getAllProjects(){
        return projectRepository.findAll();
    }

    public List<ProjectDTO> getProjectList(){
        List<Project> projectList = getAllProjects();
        return projectMapper.projectListToDTOList(projectList);
    }

    public ProjectDTO getProject(Long id){
        Project project = getProjectModel(id);
        return projectMapper.projectsToDTO(project);
    }

    public Project getProjectModel(Long projectId){
        return projectRepository.findProjectById(projectId);
    }

    public Project addProject(Project project){
        return projectRepository.save(project);
    }
}
