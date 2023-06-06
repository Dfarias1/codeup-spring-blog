package com.codeup.codeupspringblog.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

record Person(String name){}

@RestController
public class JsonController {

    @GetMapping("/json")
    public Person test() {
        return new Person("Daniel Farias");
    }
}