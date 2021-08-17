package com.example.codefellowship.controller;

import com.example.codefellowship.model.ApplicationUser;
import com.example.codefellowship.model.Post;
import com.example.codefellowship.repository.ApplicationUserRepository;
import com.example.codefellowship.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class PostController {
    @Autowired
    private ApplicationUserRepository applicationUserRepository;
    @Autowired
    private PostRepository postRepository;

    @PostMapping("/post")
    public RedirectView newPost(@RequestParam(value="body") String body) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            String username = ((UserDetails)principal).getUsername();
            ApplicationUser user = applicationUserRepository.findByUsername(username);
            SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
            Date date = new Date(System.currentTimeMillis());
            System.out.println(formatter.format(date));
            Post post = new Post(body,formatter.format(date),user);
            postRepository.save(post);
        } else {
            String username = principal.toString();
        }
        return new RedirectView("/profile");
    }
}
