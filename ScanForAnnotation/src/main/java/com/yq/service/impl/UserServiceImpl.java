package com.yq.service.impl;

import com.yq.domain.User;
import com.yq.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Simple to Introduction
 * className: UserServiceImpl
 *
 * @author EricYang
 * @version 2018/11/10 11:18
 */
@Service
@Slf4j
public class UserServiceImpl implements IUserService {

    private Map<String, User> userMap = new HashMap<>();
    {
        for(int i=0;i < 5; i++) {
            User user = new User();
            user.setId(i + "");
            user.setMail("qq" + i + "@163.com");
            user.setName("Tom" + i );
            user.setFullName("fullTom" + i );
            user.setComment("备注" + i);
            user.setAddress("地址" + i);
            user.setRegDate(new Date());
            user.setReg2Date(new Date());
            userMap.put(i+ "",user );
        }
    }

    @Override
    public User getById(String id) {
        User user = userMap.get(id);
        log.info("info find user={} by id={}", id, user);
        return user;
    }

    @Override
    public User deleteById(String id) {
        User user = userMap.remove(id);
        log.info("del user={} by id={}", id, user);
        return user;
    }

    @Override
    public User save(User user) {
        userMap.put(user.getId(), user);
        log.info("add user={} by id={}", user.getId(), user);
        return user;
    }

    @Override
    public Collection<User> getAllUsers() {
        Collection<User> users = userMap.values();
        return users;
    }
}
