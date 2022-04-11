package kz.aibat.TaskManager.controller.api;

import kz.aibat.TaskManager.dto.ProjectDTO;
import kz.aibat.TaskManager.dto.UserDTO;
import kz.aibat.TaskManager.model.Project;
import kz.aibat.TaskManager.service.AuthUserService;
import kz.aibat.TaskManager.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/api")
@RequiredArgsConstructor
public class MainRestController {

    private final ProjectService projectService;
    private final AuthUserService authUserService;

    @GetMapping("/projects")
    public ResponseEntity<List<ProjectDTO>> getAllProjects(){
        return new ResponseEntity<>(projectService.getProjectList(), HttpStatus.OK);
    }

    @GetMapping("/user_list")
    public ResponseEntity<List<UserDTO>> getUserList(){
        return new ResponseEntity<>(authUserService.getUserList(),HttpStatus.OK);
    }

//    @PostMapping(value="/addproject")
//    public ResponseEntity<String> addProject(@RequestParam(name="name") String name,
//                                             @RequestParam()){
//
//    }
}
