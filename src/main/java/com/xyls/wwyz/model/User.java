package com.xyls.wwyz.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;

/**
 * Created by zhangzf on 17-6-11.
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {
    private Integer id;
    private String userName;
    private String nickName;
    private String password;
    private String phone;
    private String email;
    private String icon;
    private Integer role;
    private Date regTime;
    private String ip;
    private Integer isActiveEmail;
    private String salt;
    private String clientId;
    private String token;

    private String message; //登录结果的描述，成功，出错信息等
    private Boolean success; //是否成功


}
