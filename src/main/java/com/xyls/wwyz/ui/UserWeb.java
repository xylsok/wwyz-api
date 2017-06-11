package com.xyls.wwyz.ui;

import com.xyls.wwyz.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by zhangzf on 17-6-11.
 */
@Api(value = "用户管理", description = "用户管理")
@RestController
@RequestMapping(value = "/user")
public class UserWeb {

    @ApiOperation(value = "用户注册", notes = "用户注册")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@RequestBody User user, HttpServletRequest request) {
        System.out.println(request);
        return null;
    }
}
