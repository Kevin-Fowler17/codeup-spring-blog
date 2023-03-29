package com.codeup.codeupspringblog.controllers;

import com.codeup.codeupspringblog.models.Post;
import com.codeup.codeupspringblog.models.User;
import com.codeup.codeupspringblog.repositories.PostRepository;
import com.codeup.codeupspringblog.repositories.UserRepository;
import com.codeup.codeupspringblog.services.Password;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static com.codeup.codeupspringblog.services.Validate.isValid;

@AllArgsConstructor
@Controller
@RequestMapping(path = "/register")
public class RegisterController {

    // dependency injection
    private final PostRepository postDao;
    private final UserRepository userDao;

    @GetMapping
    public String register(Model model){

        model.addAttribute("userExists", true);
        model.addAttribute("emailIsInvalid", true);
        model.addAttribute("passwordMismatch", true);

        return "register";
    }

    @PostMapping
    public String checkRegister(@RequestParam String username,
                                @RequestParam String email,
                                @RequestParam String password,
                                @RequestParam String confirm_password, Model model){

        model.addAttribute("stickyUsername", username);
        model.addAttribute("stickyEmail", email);

        User userNameCheck = userDao.findByUsername(username);

        model.addAttribute("userExists", true);
        model.addAttribute("emailIsInvalid", true);
        model.addAttribute("passwordMismatch", true);

        if (userNameCheck != null) {
            model.addAttribute("userExists", false);
        }

        if (!isValid(email)) {
            model.addAttribute("emailIsInvalid", false);
        }

        if (! password.equals(confirm_password)) {
            model.addAttribute("passwordMismatch", false);
        }

        if ((userNameCheck != null) || (! password.equals(confirm_password)) || (!isValid(email))) {
            return "register";
        }

        password = Password.hash(password);

        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);

        userDao.save(user);

        return "login";
    }

}
