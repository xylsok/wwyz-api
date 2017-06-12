package com.xyls.wwyz.ui;

import com.xyls.wwyz.inter.UserService;
import com.xyls.wwyz.model.LoginForm;
import com.xyls.wwyz.model.User;
import com.xyls.wwyz.utils.RequestUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by zhangzf on 17-6-11.
 */
@Api(value = "用户管理", description = "用户管理")
@RestController
@RequestMapping(value = "/user")
public class UserWeb {

    @Autowired
    UserService userService;

    private final static Logger log = LoggerFactory.getLogger(UserWeb.class);

    @ApiOperation(value = "用户注册", notes = "用户注册")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@RequestBody User user, HttpServletRequest request) {
        log.info("ip={}", RequestUtil.getUserIp(request));
        user.setIp(RequestUtil.getUserIp(request));
        userService.reg(user);
        return null;
    }

    @ApiOperation(value = "查证用户列表", notes = "查证用户列表")
    @RequestMapping(value = "/getusers", method = RequestMethod.GET)
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @ApiOperation(value = "删除用户", notes = "删除用户")
    @RequestMapping(value = "/del/{id}", method = RequestMethod.DELETE)
    public void del(@PathVariable("id") Integer id) {
        userService.del(id);
    }

    @ApiOperation(value = "更新用户头像", notes = "更新用户头像")
    @RequestMapping(value = "/updateico", method = RequestMethod.PUT)
    public void updateicon(@RequestBody User user) {
        userService.updateicon(user);
    }

    @ApiOperation(value = "详情", notes = "详情")
    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public User detail(@PathVariable("id") Integer id) {
        return userService.detail(id);
    }

    @ApiOperation(value = "用户登录", notes = "用户登录")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public User login(@RequestBody LoginForm loginForm, HttpServletRequest request) {
        User loginUser = userService.login(loginForm, request);
        return loginUser;
    }
}
