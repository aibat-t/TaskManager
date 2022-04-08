package kz.aibat.TaskManager.controller.api;

import kz.aibat.TaskManager.model.Project;
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

    @GetMapping("/project")
    public ResponseEntity<List<Project>> getAllProjects(){
        return new ResponseEntity<>(projectService.getAllProjects(), HttpStatus.Ok);
    }

    @PostMapping(value="/addproject")
    public ResponseEntity<String> addProject(@RequestParam(name="name") String name,
                                             @RequestParam()){

    }
}
