package com.yq.service;

import lombok.Data;

/**
 * Simple to Introduction
 * className: UserServiceA
 *
 * @author EricYang
 * @version 2018/10/21 15:41
 */
@Data
public class UserServiceB {
    private String userName;

    public UserServiceB(String userName) {
        this.userName =  userName;

    }
}
