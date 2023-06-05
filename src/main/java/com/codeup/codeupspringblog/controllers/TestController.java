package com.codeup.codeupspringblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

// tells the compiler that this will extend HttpServlet and set up our class to control different URI patterns
@Controller
public class TestController {
    //GET request mapping for /test
    @GetMapping("/test")
    //set response content type to text/html
    @ResponseBody
    // define method that wil return text/html
    public String test(){
        return "Hello Kotlin!";
    }
    // /parks
    @GetMapping("/parks/{park}/{message}")
    @ResponseBody
    public String parks(@PathVariable String park, @PathVariable String message){
        //Path Variables
        return "<h1>Welcome to " + park + "!</h1><p>" + message + "</p>";
    }
    @GetMapping("/parks")
    @ResponseBody
    public String parks(){
        return "Welcome to Jumanji!";
    }
    //math example
    @RequestMapping(path = "/increment/{number}", method = RequestMethod.GET)
    @ResponseBody
    public String addOne(@PathVariable int number) {
        return number + " plus one is " + (number + 1) + "!";
    }

}

