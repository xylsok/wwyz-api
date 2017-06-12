package com.xyls.wwyz.service;


import com.xyls.wwyz.dao.UserDao;
import com.xyls.wwyz.model.User;
import com.xyls.wwyz.utils.TokenHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by knix on 17/4/6.
 */
@Service("funcService")
public class FuncService {
    @Autowired
    UserDao userDao;

    //21 创建token
    public void createToken(User user) {
        if (user != null && user.getUserName() != null && !user.getUserName().equals("")) {
            String token = TokenHelper.createToken(user.getId().toString(),user.getUserName(),  user.getRole(), user.getIp(),user.getUserName()+user.getId().toString(), 1000 * 3600L * 24 * 30);
            user.setToken(token);
        } else {
            user.setMessage("不正确产品信息");
            user.setSuccess(false);
        }
    }


}
