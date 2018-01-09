package com.yq.demo.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PathVariableController {
    private int count = 0;

    @RequestMapping("/users/{username}")
    public String userProfile(@PathVariable("username") String username) {
        return String.format("Hi %s", username);
    }

    @RequestMapping("/add/{id}")
    public String post(@PathVariable("id") int id) {
        count += id;
        return String.format("Add %d, Count:%d", id, count);
    }

}
