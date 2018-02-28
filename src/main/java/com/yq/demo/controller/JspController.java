package com.yq.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class JspController {

    @RequestMapping("/action5")
    public String action05(Model model){
        model.addAttribute("message", "001");
        return "action5";
    }

}

