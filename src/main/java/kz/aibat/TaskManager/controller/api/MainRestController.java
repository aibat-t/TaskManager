package kz.aibat.TaskManager.controller.api;

import kz.aibat.TaskManager.service.AuthUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MainRestController {

    private final AuthUserService authUserService;

}
