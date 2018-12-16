

package com.yq.controller.helloworld;

import com.yq.domain.User;
import com.yq.domain.vo.UserVO;
import com.yq.service.IUserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Collection;


@Controller
@RequestMapping("/hello")
@Slf4j
public class HelloWorldController {

    @ApiOperation(value = "hello world rest")
    @GetMapping(value = "/world", produces = "application/json;charset=UTF-8")
    public String helloWorld() {
        return "Hello World!";
    }
}