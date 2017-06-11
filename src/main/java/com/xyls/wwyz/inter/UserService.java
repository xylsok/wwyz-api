package com.xyls.wwyz.inter;

import com.xyls.wwyz.model.User;

import java.util.List;

/**
 * Created by zhangzf on 17-6-11.
 */
public interface UserService {
    void  reg(User user);

    List<User> getUsers();

    void del(Integer id);

    User detail(Integer id);
}
