

package com.yq.controller;

import com.alibaba.fastjson.JSONObject;
import com.sun.management.OperatingSystemMXBean;
import com.yq.domain.User;
import com.yq.domain.vo.UserVO;
import com.yq.event.HelloWorldEvent;
import com.yq.service.IUserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.lang.management.ManagementFactory;
import com.sun.management.OperatingSystemMXBean;;
import java.util.Collection;


@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private IUserService userSvc;

    @Autowired
    private ApplicationContext applicationContext;

    @ApiOperation(value = "创建用户", notes="private")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userVO", value = "UserVO", required = true, dataType = "UserVO", paramType = "body")
    })
    @PostMapping(value = "/users", produces = "application/json;charset=UTF-8")
    public User createUser(@Valid @RequestBody UserVO userVO) {
        User user = new User();
        BeanUtils.copyProperties(userVO, user);
        userSvc.save(user);
        log.info("controller threadId={}", Thread.currentThread().getId());
        //发送用户修改事件， 这里用HelloWorld为例
        applicationContext.publishEvent(new HelloWorldEvent(user, user.getId(), "user update"));
        return user;
    }

    @ApiOperation(value = "按用户id删除", notes="private")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", defaultValue = "2", value = "userID", required = true, dataType = "string", paramType = "path"),
    })
    @DeleteMapping(value = "/users/{userId}", produces = "application/json;charset=UTF-8")
    public User delUser(@PathVariable String userId) {
        User user = (User)userSvc.deleteById(userId);
        log.info("rest del user={} by id={}", user, userId);
        return user;
    }

    @ApiOperation(value = "查询所有用户")
    @GetMapping(value = "/users", produces = "application/json;charset=UTF-8")
    public Iterable<User> findAllUsers() {
        Collection<User> users = userSvc.getAllUsers();
        log.info("rest get all users");
        return users;
    }

    @ApiOperation(value = "查询内存")
    @GetMapping(value = "/mem", produces = "application/json;charset=UTF-8")
    public String getMaxMem() {
        long max = Runtime.getRuntime().maxMemory();
        long total = Runtime.getRuntime().totalMemory();

        long byteToMb = 1024 * 1024;

        JSONObject json = new JSONObject();
        json.put("JVM 最大内存max(M)", max/byteToMb);
        json.put("JVM 总内存(M)", total/byteToMb);
        json.put("max/total", max/total);

        OperatingSystemMXBean osmxb = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        String os = System.getProperty("os.name");

        long physicalFree = osmxb.getFreePhysicalMemorySize() / byteToMb;
        long physicalTotal = osmxb.getTotalPhysicalMemorySize() / byteToMb;
        long physicalUse = physicalTotal - physicalFree;
        json.put("osVersion", os);
        json.put("物理内存已用的空间为", physicalUse);
        json.put("物理内存的空闲空间为", physicalFree);
        json.put("总物理内存", physicalTotal);


        return json.toJSONString();
    }

}