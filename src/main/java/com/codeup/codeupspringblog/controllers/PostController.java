package com.codeup.codeupspringblog.controllers;

import com.codeup.codeupspringblog.models.Post;
import com.codeup.codeupspringblog.repositories.PostRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
public class PostController {

    private final PostRepository postDao;

    public PostController(PostRepository postDao) {
        this.postDao = postDao;
    }

    @GetMapping("/posts")
    public String posts(Model model) {

        ArrayList<Post> posts = (new ArrayList<>());
//        posts.add(new Post("The Power of Gratitude", "Gratitude is a powerful emotion that can transform our lives. Studies have shown that expressing gratitude can increase happiness, reduce stress, and improve overall well-being. By focusing on the positive aspects of our lives and acknowledging the things we are grateful for, we can cultivate a more positive and fulfilling mindset. So take a moment each day to reflect on the things you are grateful for, and see how it can positively impact your life."));
//        posts.add(new Post("The Fascinating World of Cryptography", "Cryptography is the practice of secure communication in the presence of third parties. From ancient times to the modern era, cryptography has played a critical role in protecting information and secrets. Today, it is an essential component of modern technology, used in everything from online banking to national security. The study of cryptography is a fascinating and complex field, blending mathematics, computer science, and engineering to create secure systems that protect our information."));
//        posts.add(new Post("The Benefits of Mindfulness", "Mindfulness is the practice of being present and fully engaged in the current moment. By focusing our attention on the present moment, we can reduce stress, improve mental clarity, and increase overall well-being. Mindfulness practices can include meditation, yoga, or simply taking a few moments to focus on your breath. By incorporating mindfulness into your daily routine, you can cultivate a sense of calm and presence that can help you navigate life's challenges with greater ease."));

        model.addAttribute("posts", posts);

//        System.out.println(posts.size());
//        System.out.println(posts.toString());

        return "posts/index";
    }

    @GetMapping("/posts/{id}")
    public String postID(@PathVariable int id, Model model){
//        Post post = new Post("Serendipity", "Serendipity is a wonderful word that describes the occurrence of something unexpected and delightful. It's that moment of surprise and wonder when you stumble upon something beautiful or find a solution to a problem you've been struggling with. Serendipitous events are often thought to be lucky, but they can also be the result of hard work, curiosity, and a willingness to explore new possibilities. In science, serendipity has led to many significant discoveries, such as penicillin and X-rays. In art, it can inspire new creations and unexpected connections. Embracing serendipity in your life can lead to a sense of joy, spontaneity, and creativity. So, keep your eyes and mind open, and embrace the unexpected moments of serendipity that come your way.            ");
//        model.addAttribute("post", post);
        return "posts/show";
    }
    @GetMapping("posts/create")
//    @RequestMapping(path = "/posts/create", method = RequestMethod.GET)
//    @ResponseBody
    public String createPost() {
        return "/posts/create";
    }


    @PostMapping(path = "posts/create")
//    @RequestMapping(path = "posts/create", method = RequestMethod.POST)
//    @ResponseBody
    public String createdPost() {
        return "create a new post";
    }

}
