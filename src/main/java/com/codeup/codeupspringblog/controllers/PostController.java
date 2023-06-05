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
    public String postsById(@PathVariable String id){
     return "<h1>View an " + id + "post </h1>";
 }
 @GetMapping("/posts/create")
    @ResponseBody
    public String getCreate(){
     return "<h2>View the form for creating</h2>";
 }
 @PostMapping("/posts/create")
    @ResponseBody
    public String postCreate(){
     return "<h3>Create new posts</h3>";
 }
}
