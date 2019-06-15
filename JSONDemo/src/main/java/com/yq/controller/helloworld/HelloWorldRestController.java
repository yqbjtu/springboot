

package com.yq.controller.helloworld;

import com.yq.config.PermissionCheck;
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
        return "Hello World! RestController no permission check";
    }

    @ApiOperation(value = "hello world rest2")
    @GetMapping(value = "/worldRest2", produces = "application/json;charset=UTF-8")
    @PermissionCheck(id="001", description = "my desc")
    public String helloWorld2() {
        return "Hello World2! RestController. has permission check";
    }

    @ApiOperation(value = "hello world rest3")
    @GetMapping(value = "/worldRest3", produces = "application/json;charset=UTF-8")
    @PermissionCheck(id="002")
    public String helloWorld3() {
        return "Hello World3! RestController. has permission check";
    }
}