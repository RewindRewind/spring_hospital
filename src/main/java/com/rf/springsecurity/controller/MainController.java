package com.rf.springsecurity.controller;


import com.rf.springsecurity.entity.*;
import com.rf.springsecurity.services.PatientsService;
import com.rf.springsecurity.services.TreatmentService;
import com.rf.springsecurity.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static java.util.stream.Collectors.joining;

@Slf4j
@Controller
@EnableGlobalMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true,
        jsr250Enabled = true)
public class MainController {

    private UserService userService;
    private PatientsService patientsService;
    private TreatmentService treatmentService;

    @Autowired
    public MainController(UserService userService, PatientsService dishesService, TreatmentService treatmentService) {
        this.userService = userService;
        this.patientsService = dishesService;
        this.treatmentService = treatmentService;
    }

    @RequestMapping("/")
    public String getMainPage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) authentication.getPrincipal();
        model.addAttribute("login", user.getUsername());
        model.addAttribute("role", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(joining(",")));
        return "hello";
    }


    @GetMapping("/all_users")
    @PreAuthorize("hasRole('ADMIN')")
    public String getAllUsers(Model model){
        model.addAttribute("users", userService.getAllUsers().getUsers());
        model.addAttribute("roles", Role.values());
        return "users";
    }

    @GetMapping("/add_patient")
    @PreAuthorize("hasRole('RECEPTIONIST')")
    public String getAddPatient(Model model){
//        model.addAttribute("patients", patientsService.getAllPatients().getPatients());
        model.addAttribute("states", State.values());
        return "add_patient";
    }

    @GetMapping("/patients")
    @PreAuthorize("hasRole('DOCTOR') or hasRole('NURSE')")
    public String getAllPatients(Model model){
        model.addAttribute("treatments", treatmentService.getAllTreatments().getTreatments());
        model.addAttribute("patients", patientsService.getAllPatients().getPatients());
        model.addAttribute("states", State.values());
        return "patients";
    }

    @GetMapping("/discharge")
    @PreAuthorize("hasRole('DOCTOR')")
    public String getPatients(Model model){
        model.addAttribute("patients", patientsService.getAllPatients().getPatients());
        model.addAttribute("states", State.values());
        return "discharge";
    }

    @PostMapping("/all_users")
    public String addUser(User user, Model model) {
        try{
            userService.saveNewUser(user);
        }
        catch (org.springframework.dao.DataIntegrityViolationException ex){
            log.info("User " + user.getLogin() + " already exists.");
            model.addAttribute("message", "User " + user.getLogin() + " already exists");
            return "users";
        }
        catch (Exception ex){
            log.info("An error occured.");
            model.addAttribute("message", "An error occured.");
            return "users";
        }
        return "redirect:/all_users";
    }

    @PostMapping("/add_patient")
    public String addPatient(Patient patient, Model model) {
        try{
           patientsService.saveNewPatient(patient);
        } catch (Exception ex){
            log.info("An error occured.");
            model.addAttribute("message", "An error occured.");
            return "add_patient";
        }
        return "redirect:/";
    }

    @PostMapping("/patients")
    public String addTreatment(Treatment treatment, Model model) {
        try{
            treatmentService.saveNewTreatment(treatment);
        } catch (Exception ex)
        {
            log.info("An error occured.");
            model.addAttribute("message", "An error occured.");
            return "patients";
        }
        return "redirect:/patients";
    }

    @PostMapping("/discharge")
    public String discharge(Patient patient, Model model) {
        try{
            patientsService.updatePatient(patient);
        } catch (Exception ex){
            log.info("An error occured.");
            model.addAttribute("message", "An error occured.");
            return "discharge";
        }
        return "redirect:/discharge";
    }
}
