package com.yq.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.ipc.netty.http.server.HttpServerRequest;

/**
 * Created by yangqian on 2018/8/7.
 */

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String handle() {
        return "Hello World";
    }

    @GetMapping("/hello2")
    public String handle(HttpServerRequest request) {
        return "Hello World";
    }

}