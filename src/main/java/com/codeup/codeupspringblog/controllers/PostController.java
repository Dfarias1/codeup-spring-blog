package com.codeup.codeupspringblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PostController {
    @GetMapping("/posts")
    @ResponseBody
    public String posts(){
     return "posts index page";
 }
    @GetMapping("/posts/{id}")
    @ResponseBody
    public String viewPost(@PathVariable long id){
     return "Viewing post with the ID of: " + id;
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
