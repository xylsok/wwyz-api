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
    private String nickName;
    private String password;
    private String phone;
    private String email;
    private String ico;
    private Integer role;
    private Date regTime;
    private String ip;
    private Integer isActiveEmail;
    private String salt;
    private String clientId;
    private String token;

    private String message; //登录结果的描述，成功，出错信息等
    private String product; //登录的产品， 如metel，用户中心等，根据clientId而来
    private Boolean success; //是否成功
    private String wxId; //微信id, 部分接口使用.
}
