package com.rf.springsecurity.controller;

import com.rf.springsecurity.entity.Role;
import com.rf.springsecurity.entity.User;
import com.rf.springsecurity.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
public class RegistrationController {
    private UserService userService;
    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Model model) {
        if(userService.getAllUsers().getUsers().isEmpty()) {
            user.setRole(Role.ROLE_ADMIN);
            try {
                userService.saveNewUser(user);
            }
            catch (org.springframework.dao.DataIntegrityViolationException ex){
                log.info("User " + user.getLogin() + " already exists.");
                model.addAttribute("message", "User " + user.getLogin() + " already exists");
                return "registration";
            }
            catch (Exception ex){
                log.info("An error occured.");
                model.addAttribute("message", "An error occured.");
                return "registration";
            }
            return "redirect:/login";
        } else {
            log.info("Initial setup has already been completed");
            model.addAttribute("message", "Contact the administrator to sign up");
            return "redirect:/login";
        }

    }
}
