package com.codeup.codeupspringblog.controllers;

import com.codeup.codeupspringblog.models.Post;
import com.codeup.codeupspringblog.models.PostCategories;
import com.codeup.codeupspringblog.models.User;
import com.codeup.codeupspringblog.repositories.PostCategoriesRepository;
import com.codeup.codeupspringblog.repositories.PostRepository;
import com.codeup.codeupspringblog.repositories.UserRepository;
import com.codeup.codeupspringblog.services.EmailService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {
    private final PostRepository postsDao;
    private final UserRepository userDao;
    private final PostCategoriesRepository catDao;
    private final EmailService emailService;

    public PostController(PostRepository postsDao, UserRepository userDao, PostCategoriesRepository catDao, EmailService emailServiceervice) {
        this.postsDao = postsDao;
        this.userDao = userDao;
        this.catDao = catDao;
        this.emailService = emailServiceervice;
    }

    @GetMapping("/posts")
    public String viewPosts(Model model) {
        model.addAttribute("posts", postsDao.findAll());
        return "posts/index";
    }


    @GetMapping("/posts/{id}")
    public String singlePost(@PathVariable long id, Model model) {
        model.addAttribute("post", postsDao.findById(id).get());
        return "posts/show";
    }


    @GetMapping("/posts/create")
    public String showPostForm(Model model) {
        // show categories in form
        model.addAttribute("categories", catDao.findAll());
        return "/posts/create";
    }

    @GetMapping("/posts/{id}/edit")
    public String editForm(@PathVariable long id, Model model){
        Post post = postsDao.findById(id).get();
        model.addAttribute("post", postsDao.findById(id).get());
        return "posts/edit";
    }

    @PostMapping("/posts/{id}/edit")
    public String submitEditForm(@PathVariable long id, @ModelAttribute Post post){
        Post editPost = postsDao.findById(id).get();
        User user = userDao.findUserById(1L);
        editPost.setTitle(post.getTitle());
        editPost.setBody(post.getBody());
        post.setUser(user);
        postsDao.save(post);
        return "redirect:/posts/" + id;
    }


    @PostMapping("/posts/create")
//    @RequestMapping(path = "/posts/create", method = RequestMethod.POST)
    public String submitNewPost(@RequestParam (name="title")String title,
                                @RequestParam(name="body") String body,
                                @RequestParam(name = "category") List<Long> categoryIds) {
        Post post = new Post(title, body);

        List<PostCategories> categories = new ArrayList<>();

        for(long categoryId : categoryIds) {
            categories.add(catDao.findById(categoryId).get());
        }

        User user = userDao.findById(1L).get();
        post.setUser(user);
        post.setCategories(categories);
        postsDao.save(post);
        return "redirect:/posts";
    }
}