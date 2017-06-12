package com.xyls.wwyz.utils;


import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.TextCodec;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by knix on 17/4/6.
 */
public class TokenHelper {
    public static String getToken(HttpServletRequest req) {
        String header = req.getHeader(org.springframework.http.HttpHeaders.AUTHORIZATION);
        String accessToken = null;
        if (null != header) {
            String[] authTokens = header.split(" ");
            if (null != authTokens && authTokens.length > 1) {
                String scheme = authTokens[0];
                if ("Bearer".equals(scheme)) {
                    accessToken = authTokens[1];
                }
            }
        }
        if (null == accessToken || accessToken.equals("")) {
            Cookie[] cookies = req.getCookies();
            if (null != cookies) {
                for (Cookie cookie : req.getCookies()) {
                    if (cookie.getName().equals("token")) {
                        accessToken = cookie.getValue();
                    }
                }
            }
        }
        return accessToken;
    }



    public static String createToken(String userId,String userName, Integer role, String ip, String secret, long expiration) {
        try {
            JwtBuilder builder = Jwts.builder();
            String base64KeyStr = TextCodec.BASE64.encode(secret);
            Map map = new HashMap();
            if (role != null) {
                map.put("role", role);
            }
            if (userName != null && !userName.equals("")) {
                map.put("userName", userName);
            }

            map.put("ip", ip);
            builder.setClaims(map);
            builder.setSubject(userId);
            builder.setExpiration(new Date(System.currentTimeMillis() + expiration));
            String tokenCode = builder.signWith(SignatureAlgorithm.HS256, base64KeyStr).compact();
            return tokenCode;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }

    }




}
