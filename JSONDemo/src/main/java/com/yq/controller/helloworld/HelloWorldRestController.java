

package com.yq.controller.helloworld;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/hello")
@Slf4j
public class HelloWorldRestController {

    @ApiOperation(value = "hello world rest")
    @GetMapping(value = "/worldRest", produces = "application/json;charset=UTF-8")
    public String helloWorld() {
        return "Hello World! RestController";
    }
}