package com.codeup.codeupspringblog.controllers;

import com.codeup.codeupspringblog.models.Post;
import com.codeup.codeupspringblog.models.User;
import com.codeup.codeupspringblog.repositories.PostCategoriesRepository;
import com.codeup.codeupspringblog.repositories.PostRepository;
import com.codeup.codeupspringblog.repositories.UserRepository;
import com.codeup.codeupspringblog.services.EmailService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PostController {
    private final PostRepository postsDao;
    private final UserRepository userDao;
    private final PostCategoriesRepository catDao;
    private final EmailService emailService;

    public PostController(PostRepository postsDao, UserRepository userDao, PostCategoriesRepository catDao, EmailService emailService) {
        this.postsDao = postsDao;
        this.userDao = userDao;
        this.catDao = catDao;
        this.emailService = emailService;
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
    public String submitEditForm(@PathVariable long id, @ModelAttribute Post post, String method) {
        Post existingPost = postsDao.findById(id).orElse(null);
        if (existingPost == null) {
            // Handle the case when the post with the given ID does not exist
            return "redirect:/posts";
        }


        if (method != null && method.equalsIgnoreCase("DELETE")) {
            postsDao.deleteById(id);
            return "redirect:/posts";
        }

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        existingPost.setTitle(post.getTitle());
        existingPost.setBody(post.getBody());
        existingPost.setUser(user);
        postsDao.save(existingPost);

        emailService.prepareAndSend(existingPost, "A post has been updated", "Checkout the updated post from the community!", "CIA@feds.gov");

        return "redirect:/posts/" + id;
    }


    @PostMapping("/posts/create")
//    @RequestMapping(path = "/posts/create", method = RequestMethod.POST)
    public String submitNewPost(@ModelAttribute Post post) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        post.setUser(user);

        postsDao.save(post);
        emailService.prepareAndSend(post, "A new post has been POSTED", "Checkout new post from the community!", "CIA@feds.gov");
        return "redirect:/posts";
    }
    @GetMapping("/posts/{id}/delete")
    public String deletePost(@PathVariable long id) {
        postsDao.deleteById(id);
        return "redirect:/posts";
    }
}