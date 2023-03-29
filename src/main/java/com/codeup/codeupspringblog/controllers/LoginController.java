package com.codeup.codeupspringblog.controllers;

import com.codeup.codeupspringblog.models.Post;
import com.codeup.codeupspringblog.models.User;
import com.codeup.codeupspringblog.repositories.PostRepository;
import com.codeup.codeupspringblog.repositories.UserRepository;
import jakarta.annotation.Nullable;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping(path = "/login")
public class LoginController {

    // dependency injection
    private final PostRepository postDao;
    private final UserRepository userDao;


    @GetMapping
    public String login() {
        System.out.println("*********************************************************");
        return "login";
    }

    @PostMapping
    public String checkLogin(@RequestParam String username,
                             @RequestParam String password) {


//        User user = new User();
//        user.setUsername(username);
//        user.setPassword(password);

        User user = userDao.findByUsername(username);

        System.out.println("*********************************************************");
        System.out.println(user);
        System.out.println("*********************************************************");

//        User user = userDao.findById(1L).get();
//        post.setUser(user);
//
//        postDao.save(post);

        return "redirect:/posts";
    }
}
