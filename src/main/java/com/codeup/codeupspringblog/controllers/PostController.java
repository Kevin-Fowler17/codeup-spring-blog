package com.codeup.codeupspringblog.controllers;

import com.codeup.codeupspringblog.models.Post;
import com.codeup.codeupspringblog.models.User;
import com.codeup.codeupspringblog.repositories.PostRepository;
import com.codeup.codeupspringblog.repositories.UserRepository;
import jakarta.annotation.Nullable;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
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

    @GetMapping
    public String posts(@RequestParam @Nullable String search,
                        Model model,
                        HttpServletRequest request,
                        HttpServletResponse response) {

        request.getSession().getAttribute("user");

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

        if (buttonClicked.contains("edit")) {
//            return "redirect:/posts/edit/" + postID;
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
    public String createPost(Model model,
                             HttpServletRequest request,
                             HttpServletResponse response) {

        model.addAttribute("post", new Post());
        return "/posts/create";
    }

    @PostMapping(path = "/create")
    public String submitPost(@ModelAttribute Post post,
                             HttpServletRequest request,
                             HttpServletResponse response) {

        User currentUser = (User) request.getSession().getAttribute("user");

        post.setUser(currentUser);

        postDao.save(post);

        return "redirect:/posts";
    }

    @GetMapping("/{id}/edit")
    public String editPost(@PathVariable long id, Model model) {

        Post post = postDao.findById(id).get();
        model.addAttribute("post", post);

        return "/posts/create";
    }

//    @PostMapping(path = "/edit")
//    public String submitEditPost(@RequestParam(name = "id") long id,
//                                 @RequestParam(name = "title") String title,
//                                 @RequestParam(name = "body") String body) {
//
//        Post post = new Post();
//
//        post.setId(id);
//        post.setTitle(title);
//        post.setBody(body);
//
//        postDao.save(post);
//        return "redirect:/posts";
//    }

}
