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

    protected List<Task> getTasks(){
        return taskRepository.findAllByOrderByIdDesc();
    }

    public List<TaskDTO> getTaskList(){
        List<Task> tasks = getTasks();
        return taskMapper.taskListToDTOList(tasks);
    }

    public Task addTask(Task task){
        return taskRepository.save(task);
    }

    public Task getTaskModel(Long id){
        return taskRepository.getById(id);
    }

    public TaskDTO getTaskById(Long id){
        Task task = getTaskModel(id);
        return taskMapper.taskToDTO(task);
    }

    public void deleteById(Long id){
        taskRepository.deleteById(id);
    }

    public List<Task> getModelListByUsed_Id(Long id){
        return taskRepository.findByUser_Id(id);
    }

    public List<TaskDTO> getTaskListByUser_Id(Long id){
        List<Task> taskList = getModelListByUsed_Id(id);
        return taskMapper.taskListToDTOList(taskList);
    }
}
