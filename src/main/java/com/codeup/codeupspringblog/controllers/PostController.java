package com.codeup.codeupspringblog.controllers;

import com.codeup.codeupspringblog.models.Post;
import com.codeup.codeupspringblog.models.User;
import com.codeup.codeupspringblog.repositories.PostRepository;
import com.codeup.codeupspringblog.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping(path = "/posts")
public class PostController {

    private final PostRepository postDao;
    private final UserRepository userDao;

    @GetMapping()
    public String posts(Model model) {

        List<Post> posts = postDao.findAll();
        model.addAttribute("posts", posts);

        return "posts/index";
    }

    @PostMapping(path = "")
    public String editDelete(@RequestParam(name = "button") String buttonClicked) {

        String postID = buttonClicked.replace("edit", "").replace("delete", "");

        if (buttonClicked.contains("edit")) {
            return "redirect:/posts/edit/" + postID;
        } else {
            postDao.deleteById(Long.valueOf(postID));
        }

        return "redirect:/posts";

    }

    @GetMapping("/{id}")
    public String postID(@PathVariable long id, Model model){

        Post post = postDao.findById(id).get();
        model.addAttribute("post", post);

        User user = userDao.findById(1L).get();
        model.addAttribute("user", user);

        return "posts/show";
    }

    @GetMapping("/create")
    public String createPost() {
        return "/posts/create";
    }

    @PostMapping(path = "/create")
    public String submitPost(@RequestParam(name = "title") String title,
                             @RequestParam(name = "body") String body) {

        Post post = new Post();
        post.setTitle(title);
        post.setBody(body);

        User user = userDao.findById(1L).get();
        post.setUser(user);

        postDao.save(post);
        return "redirect:/posts";
    }

    @GetMapping("/edit/{id}")
    public String editPost(@PathVariable long id, Model model) {

        Post post = postDao.findById(id).get();
        model.addAttribute("post", post);

        return "/posts/edit";
    }

    @PostMapping(path = "/edit")
    public String submitEditPost(@RequestParam(name = "id") long id,
                                 @RequestParam(name = "title") String title,
                                 @RequestParam(name = "body") String body) {

        Post post = new Post();

        post.setId(id);
        post.setTitle(title);
        post.setBody(body);

        postDao.save(post);
        return "redirect:/posts";
    }

}
