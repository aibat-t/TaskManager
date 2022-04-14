package kz.aibat.TaskManager.api;

import kz.aibat.TaskManager.dto.TaskDTO;
import kz.aibat.TaskManager.dto.UserDTO;
import kz.aibat.TaskManager.model.AuthUser;
import kz.aibat.TaskManager.model.Task;
import kz.aibat.TaskManager.service.AuthUserService;
import kz.aibat.TaskManager.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping(value="/api")
@RequiredArgsConstructor
public class MainRestController {

    private final TaskService taskService;
    private final AuthUserService authUserService;

    @GetMapping(value="/user_list")
    public ResponseEntity<List<UserDTO>> getUserList(){
        return new ResponseEntity<>(authUserService.getUserList(),HttpStatus.OK);
    }

    @GetMapping(value="/tasks")
    public ResponseEntity<List<TaskDTO>> getTaskList(){
        return new ResponseEntity<>(taskService.getTaskList(), HttpStatus.OK);
    }

    @PostMapping(value="/addtask")
    public ResponseEntity<String> addTask(@RequestParam(name="name") String name,
                                              @RequestParam(name="description") String description,
                                              @RequestParam(name="deadline") String deadLine,
                                              @RequestParam(name="user") Long userId){

        AuthUser user = authUserService.getUserModelById(userId);

        if(user != null){

            Task newTask = new Task();
            newTask.setName(name);
            newTask.setDescription(description);
            newTask.setDeadLine(Date.valueOf(deadLine));
            newTask.setUser(user);

            taskService.addTask(newTask);

            return new ResponseEntity<>("success", HttpStatus.OK);
        }

        return new ResponseEntity<>("error",HttpStatus.OK);
    }

    @PostMapping(value="/updatetask")
    public ResponseEntity<String> updateTask(@RequestBody Task task){

        AuthUser user = authUserService.getUserModelById(task.getUser().getId());

        if(user != null){

            task.setUser(user);

            taskService.addTask(task);

            return new ResponseEntity<>("success", HttpStatus.OK);
        }

        return new ResponseEntity<>("error",HttpStatus.OK);
    }
}
