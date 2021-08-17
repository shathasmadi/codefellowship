package com.example.codefellowship.controller;


import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
    public class HomePage {

        @GetMapping("/")
        public String homePage(Model modelOne){
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (principal instanceof UserDetails) {
                String username = ((UserDetails)principal).getUsername();
                modelOne.addAttribute("username" , username);
            } else {
                String username = principal.toString();
            }
            return "home";
        }
    }

