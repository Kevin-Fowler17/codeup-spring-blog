package com.codeup.codeupspringblog.controllers;

import com.codeup.codeupspringblog.models.Post;
import com.codeup.codeupspringblog.models.User;
import com.codeup.codeupspringblog.repositories.PostRepository;
import com.codeup.codeupspringblog.repositories.UserRepository;
import com.codeup.codeupspringblog.services.EmailService;
import jakarta.annotation.Nullable;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping(path = "/posts")
public class PostController {

    // dependency injection
    private final PostRepository postDao;
    private final UserRepository userDao;
    private final EmailService emailService;

    @GetMapping
    public String posts(@RequestParam @Nullable String search,
                        Model model) {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        model.addAttribute("user", user);

        if (search != null) {
            List<Post> posts = (List<Post>) postDao.findLikeTitleOrBody(search);
            model.addAttribute("posts", posts);
        } else {
            List<Post> posts = postDao.findAll();
            model.addAttribute("posts", posts);
        }

        return "/posts/index";
    }

    @PostMapping(path = "")
    public String editDelete(@RequestParam(name = "button") String buttonClicked) {

        String postID = buttonClicked.replace("edit", "").replace("delete", "");

        System.out.println("***************");
        System.out.println(postID);
        System.out.println("***************");

        if (buttonClicked.contains("edit")) {
            return "redirect:/posts/" + postID + "/edit";
        } else {
            postDao.deleteById(Long.valueOf(postID));
        }

        return "redirect:/posts";

    }

    @GetMapping("/{id}")
    public String postID(@PathVariable long id, Model model){

        Post post = postDao.findById(id).get();
        model.addAttribute("post", post);

        return "/posts/show";
    }

    @GetMapping("/create")
    public String createPost(Model model) {

        model.addAttribute("post", new Post());
        return "/posts/create";
    }

    @PostMapping(path = "/create")
    public String submitPost(@ModelAttribute Post post,
                             Model model) {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        model.addAttribute("user", user);

        post.setUser(user);

        postDao.save(post);

        emailService.prepareAndSend(post, "New blog created: " + post.getTitle(), "A new blog was created for your account.");

        return "redirect:/posts";
    }

    @GetMapping("/{id}/edit")
    public String editPost(@PathVariable long id, Model model) {

        Post post = postDao.findById(id).get();
        model.addAttribute("post", post);

        return "/posts/create";
    }

}
