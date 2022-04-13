package kz.aibat.TaskManager.service;

import kz.aibat.TaskManager.dto.TaskDTO;
import kz.aibat.TaskManager.mapper.TaskMapper;
import kz.aibat.TaskManager.model.Task;
import kz.aibat.TaskManager.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    protected List<Task> getUsers(){
        return taskRepository.findAll();
    }

    public List<TaskDTO> getUserList(){
        List<Task> tasks = getUsers();
        return taskMapper.taskListToDTOList(tasks);
    }

    public Task addTask(Task task){
        return taskRepository.save(task);
    }
}
