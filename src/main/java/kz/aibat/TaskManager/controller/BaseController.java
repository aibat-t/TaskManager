package kz.aibat.TaskManager.controller;

import kz.aibat.TaskManager.model.AuthUser;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class BaseController {

    protected AuthUser getCurrentUser(){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(!(authentication instanceof AnonymousAuthenticationToken)){
            AuthUser currentUser = (AuthUser) authentication.getPrincipal();
            return currentUser;
        }
        return null;
    }
}
