package kz.aibat.TaskManager.mapper;

import kz.aibat.TaskManager.dto.ProjectDTO;
import kz.aibat.TaskManager.model.Project;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProjectMapper {
    ProjectDTO projectsToDTO(Project project);
    List<ProjectDTO> projectListToDTOList(List<Project> projectList);
}
