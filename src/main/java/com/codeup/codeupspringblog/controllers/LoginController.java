package com.codeup.codeupspringblog.controllers;

import com.codeup.codeupspringblog.models.Post;
import com.codeup.codeupspringblog.models.User;
import com.codeup.codeupspringblog.repositories.PostRepository;
import com.codeup.codeupspringblog.repositories.UserRepository;
import com.codeup.codeupspringblog.services.Password;
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
    public String login(Model model) {
        model.addAttribute("hideErrMsg", true);
        return "login";
    }

    @PostMapping
    public String checkLogin(@RequestParam String username,
                             @RequestParam String password, Model model) {

        User user = userDao.findByUsername(username);

        if (user == null) {
            model.addAttribute("stickyUsername", username);
            model.addAttribute("hideErrMsg", false);
            return "login";
        }

        boolean validAttempt = Password.check(password, user.getPassword());

        if (validAttempt) {
            // set session userExists
//            session.setAttribute("user", user);
            model.addAttribute("hideErrMsg", true);
        } else {
            model.addAttribute("hideErrMsg", false);
            return "login";
        }

        return "redirect:/posts";
    }
}
