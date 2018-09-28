package com.yq.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.yq.demo.other.UserDemo;

@Controller
public class ThymeLeafController {

    /**
     * 测试hello
     * @return
     */
    @RequestMapping(value = "/thymeleafHello",method = RequestMethod.GET)
    public String hello(Model model) {
        model.addAttribute("name", "赵刘");
        UserDemo user = new UserDemo(2, "Tom", "tom@163.com", "tom is a developer");
        return "thymeleafHello";
    }

    /**
     * 测试thymeleafHello2?name=zhangSan
     * @return
     */
    @RequestMapping(value = "/thymeleafHello2",method = RequestMethod.GET)
    public String hello2(Model model, @RequestParam("name") String name) {

        UserDemo user = new UserDemo(3, "John", "john@163.com", "John is a designer");
        model.addAttribute("name", name);
        model.addAttribute("user", user);
        return "thymeleafHello";
    }
}
