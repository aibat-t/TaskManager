package kz.aibat.TaskManager.controller.api;

import kz.aibat.TaskManager.dto.UserDTO;
import kz.aibat.TaskManager.service.AuthUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/api")
@RequiredArgsConstructor
public class MainRestController {
    private final AuthUserService authUserService;

    @GetMapping("/user_list")
    public ResponseEntity<List<UserDTO>> getUserList(){
        return new ResponseEntity<>(authUserService.getUserList(),HttpStatus.OK);
    }
}
