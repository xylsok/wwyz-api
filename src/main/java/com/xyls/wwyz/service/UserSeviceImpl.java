package com.xyls.wwyz.service;

import com.xyls.wwyz.dao.UserDao;
import com.xyls.wwyz.inter.UserService;
import com.xyls.wwyz.model.User;
import com.xyls.wwyz.utils.PasswordHelper;
import com.xyls.wwyz.utils.UserUtil;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Created by zhangzf on 17-6-11.
 */
@Service("usrtService")
public class UserSeviceImpl implements UserService {

    private RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();

    @Autowired
    UserDao userDao;

    @Autowired
    FuncService funcService;

    @Override
    public void reg(User user) {
        user.setRegTime(new Date());
        user.setIsActiveEmail(0);
        user.setSalt(randomNumberGenerator.nextBytes().toHex());
        user.setPassword(PasswordHelper.encryptPassword(user.getPassword(), user.getSalt()));
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

    @Override
    public User login(User user, HttpServletRequest request) {
        if (user.equals("") || user.equals("")) {
            User loginUser = new User();
            loginUser.setSuccess(false);
            loginUser.setMessage("用户名或密码不能为空");
            return user;
        }
        //04. 账密得user
        Optional<User> userByPwd = getUserByPwd(user.getUserName(), user.getPassword());
        if (userByPwd.isPresent()) {
            User newUser = userByPwd.get();
            if (newUser.getUserName() == null || user.getUserName().equals("")) {
                user.setMessage("(UL)");
                user.setSuccess(false);
                return user;
            }
            user.setSuccess(true);
            //21. 生成token
            funcService.createToken(newUser);
            return newUser;
        } else {
            return createBadUser("错误的用户名或密码(WR)");
        }
    }

    public Optional<User> getUserByPwd(String username, String password) {
        User u = userDao.getByName(username);
        if (u != null) {
            //未激活的用户不允许登录
            if (u.getIsActiveEmail() <= 0) {
                return Optional.of(createBadUser("邮箱未激活"));
            }
            //密码加盐加密
            String inputPassword = PasswordHelper.encryptPassword(password, u.getSalt());
            String existPassword = u.getPassword();
            //如果不是超级密码, 且密码也不正确, 返回一个错误.
            if (!inputPassword.equals(existPassword)) {
                return Optional.of(createBadUser("密码不正确"));
            }
            //密码通过
            return Optional.of(UserUtil.translateUser(u));
        }
        return Optional.empty();
    }

    //20 创建非正常用户
    public User createBadUser(String msg) {
        User user = new User();
        user.setSuccess(false);
        user.setMessage(msg);
        return user;
    }
}
