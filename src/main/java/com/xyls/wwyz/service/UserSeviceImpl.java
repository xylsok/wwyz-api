package com.xyls.wwyz.service;

import com.xyls.wwyz.dao.UserDao;
import com.xyls.wwyz.inter.UserService;
import com.xyls.wwyz.model.LoginForm;
import com.xyls.wwyz.model.User;
import com.xyls.wwyz.utils.CreateCode;
import com.xyls.wwyz.utils.PasswordHelper;
import com.xyls.wwyz.utils.UserUtil;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpServletRequest;
import java.io.FileOutputStream;
import java.io.OutputStream;
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
    public User login(LoginForm loginForm, HttpServletRequest request) {
        if (loginForm.getUsername().equals("") || loginForm.getPassword().equals("")) {
            User loginUser = new User();
            loginUser.setSuccess(false);
            loginUser.setMessage("用户名或密码不能为空");
            return loginUser;
        }
        //04. 账密得user
        Optional<User> userByPwd = getUserByPwd(loginForm.getUsername(), loginForm.getPassword());
        if (userByPwd.isPresent()) {
            User newUser = userByPwd.get();
            if (newUser.getUserName() == null || newUser.getUserName().equals("")) {
                newUser.setSuccess(false);
                return newUser;
            }
            newUser.setSuccess(true);
            //21. 生成token
            funcService.createToken(newUser);
            return newUser;
        } else {
            return createBadUser("错误的用户名或密码(WR)");
        }
    }

    @Override
    public void updateicon(User user) {
        String fileName = GenerateImage(user.getIcon());
        user.setIcon(fileName);
        userDao.updateicon(user);
    }

    // base64字符串转化成图片
    public static String GenerateImage(String imgStr) { //对字节数组字符串进行Base64解码并生成图片
        if (imgStr == null) {
            return null;
        }
        String fileName = CreateCode.getCode();
        imgStr = imgStr.replace("data:image/png;base64,","");
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            // Base64解码
            byte[] b = decoder.decodeBuffer(imgStr);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {// 调整异常数据
                    b[i] += 256;
                }
            }
            // 生成jpeg图片
            String imgFilePath = "/data/filecenter/wwyz-file/" + fileName + ".png";//新生成的图片
            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(b);
            out.flush();
            out.close();
            return fileName + ".png";
        } catch (Exception e) {
            return null;
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
