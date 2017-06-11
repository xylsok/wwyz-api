package com.xyls.wwyz.service;

import com.xyls.wwyz.dao.UserDao;
import com.xyls.wwyz.inter.UserService;
import com.xyls.wwyz.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by zhangzf on 17-6-11.
 */
@Service("usrtService")
public class UserSeviceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public void reg(User user) {
        user.setRegTime(new Date());
        userDao.save(user);
    }

    @Override
    public List<User> getUsers() {
        return userDao.getUsers();
    }

    @Override
    public void del(Integer id) {
        userDao.deleteById(id);
    }

    @Override
    public User detail(Integer id) {
        return userDao.findById(id);
    }
}
