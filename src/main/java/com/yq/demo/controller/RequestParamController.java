package com.yq.demo.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yq.demo.domain.User;

@RestController
public class RequestParamController {
    // Spring uses the Jackson JSON library to automatically marshal instances of type Greeting into JSON
    //@RequestMapping maps all HTTP operations by default. Use @RequestMapping(method=GET) to narrow this mapping.

    //userDemo?id=001&name=Eric&email=bjxjsx@163.com
    @RequestMapping("/userDemo")
    public User userDemo(@RequestParam("id") Integer id,
        @RequestParam("name") String name, @RequestParam String email) {

        User user = new User(id, name, email, null);
        return user;
    }

    //arrayListIntegerDemo?id=1&id=3&id=7?
    @RequestMapping("/arrayListIntegerDemo")
    public User arrayListIntegerDemo(@RequestParam("id") List<Integer> ids){
        String str = Arrays.deepToString(ids.toArray());
        User user = new User(ids.size(), "defaultName", null, str);
        return user;
    }

    //arrayDemo?id=1,5,9,34,67
    @RequestMapping("/arrayDemo")
    public User arrayDemo(@RequestParam("id") Integer[] ids){
        String str = Arrays.deepToString(ids);
        User user = new User(ids.length, "defaultName", null, str);
        return user;
    }

}


