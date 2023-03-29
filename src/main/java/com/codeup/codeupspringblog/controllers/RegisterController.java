package com.codeup.codeupspringblog.controllers;

import com.codeup.codeupspringblog.repositories.PostRepository;
import com.codeup.codeupspringblog.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@AllArgsConstructor
@Controller
@RequestMapping(path = "/register")
public class RegisterController {

    // dependency injection
    private final PostRepository postDao;
    private final UserRepository userDao;

    @GetMapping
    public String register(){
        return "register";
    }

    @PostMapping
    public String checkRegister(){
        return "login";
    }

}
