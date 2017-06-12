package com.xyls.wwyz.utils;


import com.xyls.wwyz.model.User;

/**
 * Created by knix on 17/4/6.
 */
public class UserUtil {
    public static User translateUser(User u) {
        if (u == null) {
            return null;
        }
        User user = new User();
        user.setId(u.getId());
        user.setNickName(u.getNickName());
        user.setRole(u.getRole());
        user.setIcon(u.getIcon());
        user.setUserName(u.getUserName());
        return user;
    }

    public static boolean testPassword( User u, String password) {
        return false;
    }

    public static boolean testEmailIsActive( User u, String name) {
        return false;
    }

    public static boolean testPhoneIsActive( User u, String name) {
        return false;

    }

}
