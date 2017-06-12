package com.xyls.wwyz.utils;


import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public class PasswordHelper {

    private static RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();

//    private String algorithmName = "md5";
//    private int hashIterations = 2;

//    public void setRandomNumberGenerator(RandomNumberGenerator randomNumberGenerator) {
//        this.randomNumberGenerator = randomNumberGenerator;
//    }
//
//    public void setAlgorithmName(String algorithmName) {
//        this.algorithmName = algorithmName;
//    }
//
//    public void setHashIterations(int hashIterations) {
//        this.hashIterations = hashIterations;
//    }

//    public void encryptPassword(User user) {
//        if (null != user() && !("".equals(user.getPassword()))) {
//            user.setSalt(randomNumberGenerator.nextBytes().toHex());
//            String newPassword = new SimpleHash(
//                    algorithmName,
//                    user.getPassword(),
//                    ByteSource.Util.bytes(user.getPassword() + user.getSalt()),
//                    hashIterations).toHex();
//            user.setPassword(newPassword);
//        }
//    }

    /**
     * 根据明文密码和盐值加密
     *
     * @param password
     * @param salt
     */
    public static String encryptPassword(String password, String salt) {
        if (password == null) password = "";
        if (salt == null) salt = "";
        String algorithmName = "md5";
        int hashIterations = 2;
        String pwd = new SimpleHash/**/(
                algorithmName,
                password,
                ByteSource.Util.bytes(password + salt),
                hashIterations).toHex();
        return pwd;
    }

}
