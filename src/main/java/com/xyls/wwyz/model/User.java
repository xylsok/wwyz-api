package com.xyls.wwyz.model;

import lombok.Data;

import java.util.Date;

/**
 * Created by zhangzf on 17-6-11.
 */
@Data
public class User {
    private Integer id;
    private String userName;
    private String password;
    private String phone;
    private String email;
    private String ico;
    private Date createTime;
}
