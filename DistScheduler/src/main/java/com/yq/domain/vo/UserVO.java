package com.yq.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Simple to Introduction
 * className: UserVO
 *
 * @author EricYang
 * @version 2018/11/16 18:33
 */
@Data
@NoArgsConstructor
public class UserVO {
    @ApiModelProperty(value = "id", required = true , example = "06")
    private String id;

    @ApiModelProperty(value = "名称", required = true ,example = "tom")
    private String name;

    @ApiModelProperty(value = "全名", required = true ,example = "tomFull")
    private String fullName;

    @ApiModelProperty(value = "备注", required = true ,example = "张三")
    private String comment;

    @ApiModelProperty(value = "邮箱", required = true ,example = "06")
    private String mail;

    @ApiModelProperty(value = "地址", required = true ,example = "06")
    private String address;

    @ApiModelProperty(value = "注册日志", required = false ,example = "2018-11-16 16:07:08")
    private Date regDate;

    @ApiModelProperty(value = "注册日期2", required = false ,example = "")
    private Date reg2Date;
}
