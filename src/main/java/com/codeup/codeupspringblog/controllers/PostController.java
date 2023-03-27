package com.codeup.codeupspringblog.controllers;

import com.codeup.codeupspringblog.models.Post;
import com.codeup.codeupspringblog.repositories.PostRepository;
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

    @GetMapping()
    public String posts(Model model) {

        List<Post> posts = postDao.findAll();
        model.addAttribute("posts", posts);

        return "posts/index";
    }

    @GetMapping("/{id}")
    public String postID(@PathVariable long id, Model model){

        Post post = postDao.findById(id).get();
        model.addAttribute("post", post);

        return "posts/show";
    }

    @GetMapping("/create")
    public String createPost() {
        return "/posts/create";
    }

    @PostMapping(path = "/create")
    public String createdPost() {
        Post post = new Post();
        post.setTitle("post title");
        post.setBody("post body");

        postDao.save(post);
        return "/posts/index";
    }

}
