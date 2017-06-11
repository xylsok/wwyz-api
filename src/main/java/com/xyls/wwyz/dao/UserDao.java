package com.xyls.wwyz.dao;

import com.xyls.wwyz.model.User;
import net.gddata.wwyz.tables.records.UserRecord;
import org.springframework.stereotype.Component;

import java.util.List;

import static net.gddata.wwyz.tables.User.USER;

/**
 * Created by zhangzf on 17-6-11.
 */
@Component
public class UserDao extends JooqDao<UserRecord,User,Integer>{

    protected UserDao() {
        super(USER, User.class);
    }

    @Override
    protected Integer getId(User user) {
        return user.getId();
    }

    public List<User> getUsers() {
        return this.findAll();
    }
}
