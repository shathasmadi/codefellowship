package com.example.codefellowship.controller;

import com.example.codefellowship.model.ApplicationUser;
import com.example.codefellowship.repository.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.ArrayList;


@Controller
public class ApplicationUserController {

    @Autowired
    ApplicationUserRepository  applicationUserRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder   ;



    @GetMapping("/login")
    public String loginPage(){
    return "signin";
}

    @GetMapping("/signup")
    public String signupPage() {
        return "signup";
    }

   @PostMapping("/signup")
      public RedirectView signUp(@RequestParam(value ="username") String username,@RequestParam(value = "password") String password, @RequestParam(value = "firstName") String firstName, @RequestParam(value = "lastName") String lastName, @RequestParam(value = "dateOfBirth") String dateOfBirth,@RequestParam(value = "bio") String bio ){

       ApplicationUser newUser = new ApplicationUser(username,bCryptPasswordEncoder.encode(password) , firstName, lastName, dateOfBirth,bio );
       applicationUserRepository.save(newUser);
       return  new RedirectView("/login");

   }

        @GetMapping("/users/{id}")
          public String profilePage(Model modelOne, @PathVariable("id") int id){
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (principal instanceof UserDetails) {
                String username = ((UserDetails)principal).getUsername();
                modelOne.addAttribute("username" , username);
                modelOne.addAttribute("user",  applicationUserRepository.findById(id).get());
            } else {
                String username = principal.toString();
            }
            return "user";

   }
        @GetMapping("/profile")
        public String profile(Model modelOne){
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (principal instanceof UserDetails) {
                String username = ((UserDetails)principal).getUsername();
                modelOne.addAttribute("username" , username);
                modelOne.addAttribute("user" , applicationUserRepository.findByUsername(username));
            } else {
                String username = principal.toString();
            }
        return "profile";
    }

    @PostMapping("/follow/{id}")
    public RedirectView followFunction(Principal p,@PathVariable("id") int id){
        ApplicationUser user=applicationUserRepository.findByUsername(p.getName());
        System.out.println(user.getFollowing().toString());
        ApplicationUser followedUser=applicationUserRepository.findById(id).get();
        followedUser.getFollowers().add(user);
        user.getFollowing().add(followedUser);
        applicationUserRepository.save(followedUser);
        applicationUserRepository.save(user);
        return new RedirectView("/users/{id}");
    }

    @GetMapping("/feed")
    public String feed(Principal p,Model modelOne){
            ApplicationUser user=applicationUserRepository.findByUsername(p.getName());
            modelOne.addAttribute("user",user);

        return "feed";
    }

    @GetMapping("/findusers")
    public String findUsers(Model modelOne){
        ArrayList<ApplicationUser> users= (ArrayList<ApplicationUser>)applicationUserRepository.findAll();
        modelOne.addAttribute("users",users);
        return "findusers";
    }
    }

