package com.xyls.wwyz.utils;

import java.util.UUID;

/**
 * Created by zhangzf on 17/6/12.
 */
public class CreateCode {
    public static String[] chars = new String[]{
            "a", "b", "c", "d", "e", "f", "g", "h",
            "i", "j", "k", "l", "m", "n", "o", "p",
            "q", "r", "s", "t", "u", "v", "w", "x",
            "y", "z", "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "_", "-", "A", "B",
            "C", "D", "E", "F", "G", "H", "I", "J",
            "K", "L", "M", "N", "O", "P", "Q", "R",
            "S", "T", "U", "V", "W", "X", "Y", "Z"};

    public static String getCode() {
        StringBuffer shortBuffer = new StringBuffer();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        for (int i = 0; i < 8; i++) {
            String str = uuid.substring(i * 4, i * 4 + 4);
            int x = Integer.parseInt(str, 16);
            shortBuffer.append(chars[x % 64]);
        }
        return shortBuffer.toString();
    }

    public static String get4Num() {
        StringBuffer n = new StringBuffer();
        for (int i = 0; i < 4; i++) {
            int num = (int) (Math.random() * 10);
            System.out.println(num);
            n.append(num);
        }
        return n.toString();
    }
}
