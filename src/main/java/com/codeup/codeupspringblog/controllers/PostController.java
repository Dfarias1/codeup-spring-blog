package com.codeup.codeupspringblog.controllers;

import com.codeup.codeupspringblog.models.Post;
import com.codeup.codeupspringblog.repositories.PostRepository;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class PostController {

    private final PostRepository postsDao;

    public PostController(PostRepository postsDao) {
        this.postsDao = postsDao;
    }

    @GetMapping("/posts")

    public String posts(Model model) {
        List<Post> posts = postsDao.findAll();
        model.addAttribute("posts", posts);

        return "posts/index";
    }


    @GetMapping("/posts/{id}")

    public String post(@PathVariable Long id, Model model) {
        Post p1 = new Post(1L,"YOOO", "This is my first post!");
        model.addAttribute("title", p1.getTitle());
        model.addAttribute("body", p1.getBody());
        return "posts/show";
    }

    @GetMapping("/posts/create")
    public String create() {
        return "posts/create";
    }

    @PostMapping("/posts/create")

    public String createPost(@RequestParam String title, String body, Model model) {
        model.addAttribute("title", title);
        model.addAttribute("body", body);
        Post post = new Post(title, body);
        postsDao.save(post);

        return "redirect:/posts";
    }


}