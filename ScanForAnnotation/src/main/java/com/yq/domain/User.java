

package com.yq.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Date;

/**
 * Simple to Introduction
 * className: User
 *
 * @author EricYang
 * @version 2018/6/29 23:43
 */
@Data
@JsonIgnoreProperties(value = {"fullName", "comment"})
public class User {
    private String id;
    private String name;
    private String fullName;
    private String comment;
    private String mail;

    @JsonIgnore
    private String address;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date regDate;

    private Date reg2Date;
}
