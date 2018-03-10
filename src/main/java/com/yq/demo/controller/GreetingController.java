package com.yq.demo.controller;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.yq.demo.domain.Greeting;

@Controller
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    // Spring uses the Jackson JSON library to automatically marshal instances of type Greeting into JSON
    //@RequestMapping maps all HTTP operations by default. Use @RequestMapping(method=GET) to narrow this mapping.
    /*@RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new Greeting(counter.incrementAndGet(),
                            String.format(template, name));
    }*/

    @GetMapping("/greeting")
    public String greetingForm(Model model) {
        Greeting greeting  = new Greeting();
        greeting.setId(99L);
        greeting.setContent("Hello submit form!");
        model.addAttribute("greetingObj", greeting);
        //resources/templates/greeting.html
        return "greeting";
    }

    @PostMapping("/greeting")
    public String greetingSubmit(@ModelAttribute("grt") Greeting grtObject) {
        //resources/templates/result.html
        return "result";
    }
}
