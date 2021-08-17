package com.example.codefellowship.controller;

import com.example.codefellowship.model.ApplicationUser;
import com.example.codefellowship.repository.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;


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

            modelOne.addAttribute("user",  applicationUserRepository.findById(id).get());
            return "user";

   }
}
