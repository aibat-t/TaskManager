package kz.aibat.TaskManager.controller;

import kz.aibat.TaskManager.model.AuthUser;
import kz.aibat.TaskManager.service.AuthUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class MainController extends BaseController{

    private final AuthUserService authUserService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping(value="/")
    public String indexPage(Model model){
        model.addAttribute("currentUser", getCurrentUser());
        return "index";
    }

    @GetMapping(value = "/signin")
    public String signInPage(Model model){
        model.addAttribute("currentUser", getCurrentUser());
        return "signin";
    }

    @GetMapping(value="/signup")
    public String singUpPAge(Model model){
        model.addAttribute("currentUser", getCurrentUser());
        return "signup";
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
}
