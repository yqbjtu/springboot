package com.yq.demo.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PathVariableController {
    private int count = 0;
    private static final Log log = LogFactory.getLog(PathVariableController.class);

    @RequestMapping("/users/{username}")
    public String userProfile(@PathVariable("username") String username) {
        return String.format("Hi %s", username);
    }

    @RequestMapping("/add/{id}")
    public String post(@PathVariable("id") int id) {
        count += id;
        log.info("add " + id);
        return String.format("Add %d, Count:%d", id, count);
    }

}
