package kz.aibat.TaskManager.mapper;

import kz.aibat.TaskManager.dto.TaskDTO;
import kz.aibat.TaskManager.model.Task;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    TaskDTO taskToDTO(Task task);
    List<TaskDTO> taskListToDTOList(List<Task> taskList);
}
