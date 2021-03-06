package kz.aibat.TaskManager.controller;

import kz.aibat.TaskManager.dto.TaskDTO;
import kz.aibat.TaskManager.dto.UserDTO;
import kz.aibat.TaskManager.model.AuthUser;
import kz.aibat.TaskManager.model.Task;
import kz.aibat.TaskManager.service.AuthUserService;
import kz.aibat.TaskManager.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController extends BaseController{

    private final AuthUserService authUserService;
    private final PasswordEncoder passwordEncoder;
    private final TaskService taskService;

    @GetMapping(value="/")
    public String indexPage(Model model){
        model.addAttribute("currentUser", getCurrentUser());
        return "/index";
    }

    @GetMapping(value = "/signin")
    public String signInPage(Model model){
        model.addAttribute("currentUser", getCurrentUser());
        return "/signin";
    }

    @GetMapping(value="/signup")
    public String singUpPAge(Model model){
        model.addAttribute("currentUser", getCurrentUser());
        return "/signup";
    }

    @PostMapping(value = "tosignup")
    public String toSignUp(@RequestParam(name = "user_email") String email,
                           @RequestParam(name = "user_full_name") String fullName,
                           @RequestParam(name = "user_password") String password,
                           @RequestParam(name = "user_re_password") String rePassword){

        if(password.equals(rePassword)){

            AuthUser checkUser = authUserService.getUserByEmail(email);
            if(checkUser == null){
                AuthUser user = new AuthUser();
                user.setEmail(email);
                user.setFullName(fullName);
                user.setPassword(passwordEncoder.encode(password));

                authUserService.createUser(user);

                return "redirect:/signup?success";
            }
            return "redirect:/signup?emailerror";
        }
        return "redirect:/signup?passworderror";
    }

    @GetMapping(value = "/tasks")
    @PreAuthorize("hasAnyRole('ROLE_MODERATOR')")
    public String tasksPage(Model model){
        model.addAttribute("currentUser", getCurrentUser());
        return "/taskspage";
    }

    @GetMapping(value = "/task/{id}")
    @PreAuthorize("hasAnyRole('ROLE_MODERATOR')")
    public String taskPage(Model model,
                           @PathVariable(name="id") Long id){


        TaskDTO task = taskService.getTaskById(id);
        List<UserDTO> userList = authUserService.getUserList();

        model.addAttribute("userList", userList);
        model.addAttribute("task", task);
        model.addAttribute("currentUser", getCurrentUser());
        return "/taskedit";
    }

    @PostMapping(value = "/deletetask")
    @PreAuthorize("hasAnyRole('ROLE_MODERATOR')")
    public String deleteTask(Model model,
                            @RequestParam(name="task_id") Long id){

        taskService.deleteById(id);

        model.addAttribute("currentUser", getCurrentUser());
        return "redirect:/tasks";
    }

    @GetMapping(value="/mytasks")
    @PreAuthorize("hasAnyRole('ROLE_USER')")
    public String myTasksPage(Model model){
        model.addAttribute("currentUser", getCurrentUser());
        return "/mytaskspage";
    }

    @GetMapping(value = "/mytask/{id}")
    @PreAuthorize("hasAnyRole('ROLE_USER')")
    public String myTaskPage(Model model,
                           @PathVariable(name="id") Long id){

        TaskDTO task = taskService.getTaskById(id);
        List<UserDTO> userList = authUserService.getUserList();

        model.addAttribute("userList", userList);
        model.addAttribute("task", task);
        model.addAttribute("currentUser", getCurrentUser());
        return "/mytaskedit";
    }

    @GetMapping(value = "/profile")
    @PreAuthorize("isAuthenticated()")
    public String profilePage(Model model){
        model.addAttribute("currentUser", getCurrentUser());
        return "profile";
    }

    @PostMapping(value = "updatepassword")
    @PreAuthorize("isAuthenticated()")
    public String toUpdatePassword(@RequestParam(name = "user_old_password") String oldPassword,
                                   @RequestParam(name = "user_new_password") String newPassword,
                                   @RequestParam(name = "user_new_re_password") String reNewPassword){

        if(newPassword.equals(reNewPassword)){

            AuthUser currentUser = getCurrentUser();
            AuthUser checkUser = authUserService.getUserByEmail(currentUser.getEmail());

            if(passwordEncoder.matches(oldPassword, checkUser.getPassword())){

                currentUser.setPassword(passwordEncoder.encode(newPassword));
                authUserService.updateUser(currentUser);
                return "redirect:/profile?success";
            }
            return "redirect:/profile?oldpassworderror";
        }
        return "redirect:/profile?passworderror";
    }
}
