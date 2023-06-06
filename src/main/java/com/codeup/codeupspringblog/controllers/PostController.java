package com.codeup.codeupspringblog.controllers;

import com.codeup.codeupspringblog.models.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {
    @GetMapping("/posts")
    public String viewPost(Model model){
        List<Post> posts = new ArrayList<>();
        posts.add(new Post("first post", "this my first post."));
        posts.add(new Post("second post", "this my second post."));
        model.addAttribute("posts", posts);
        return "posts/index";
 }
    @GetMapping("/posts/{id}")
    public String singlePost(@PathVariable long id){

        return "posts/show";
 }
    @GetMapping("/posts/create")
    @ResponseBody
    public String showPostForm(){

        return "View the form for creating";
 }
    @PostMapping("/posts/create")
    @ResponseBody
    public String submitNewPost(){

        return "Create new posts";
 }
}
