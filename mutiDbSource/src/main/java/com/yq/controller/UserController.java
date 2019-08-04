

package com.yq.controller;

import com.yq.mapper.UserMapper;
import com.yq.domain.User;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/user")
public class UserController {
    private Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserMapper userMapper;

//    @Autowired
//    IUserService  userSvc;

    @ApiOperation(value = "按用户id查询", notes="private")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", defaultValue = "2", value = "userID", required = true, dataType = "int", paramType = "path"),
    })
    @GetMapping(value = "/users/{userId}", produces = "application/json;charset=UTF-8")
    public User getUser(@PathVariable int userId) {
        User userInfo;
        userInfo = userId % 2 == 1 ? userMapper.selectByOddUserId(userId) : userMapper.selectByEvenUserId(userId);
        log.info("{}->={}", userInfo.getId(), userInfo.getRemarks());

        return userInfo;
    }

//    @ApiOperation(value = "按用户id查询", notes="private")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "userId", defaultValue = "2", value = "userID", required = true, dataType = "string", paramType = "path"),
//    })
//    @DeleteMapping(value = "/users/{userId}", produces = "application/json;charset=UTF-8")
//    public User delUser(@PathVariable String userId) {
//        User user = (User)userSvc.deleteById(userId);
//        log.info("rest del user={} by id={}", user, userId);
//        log.debug("rest del user={} by id={}", user, userId);
//        return user;
//    }


}