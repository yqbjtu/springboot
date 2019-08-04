package com.yq.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class User implements Serializable {
    private Integer id;

    private String userName;

    private Integer userAge;

    private String address;

    private Date addTime;

    private String remarks;
    
}