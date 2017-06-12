package com.xyls.wwyz.utils;

import net.gddata.common.util.StringUtil;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zhangzf on 17-6-11.

 */
public class RequestUtil {
    public static String getIp(HttpServletRequest request) {
        List<String> ignoreList = new ArrayList<>();
        ignoreList.add("unknown");
        String userIp = StringUtil.getNotEmptyWithIgnores(ignoreList,
                request.getHeader("X-real-ip"),
                request.getHeader("X-Forwarded-For"),
                request.getRemoteAddr());
        if(userIp.equals("0::1") || userIp.equals("0:0:0:0:0:0:0:1") || userIp.equals("localhost") || userIp.equals("0.0.0.0"))
        {
            userIp = "127.0.0.1";
        }
        return userIp;
    }

    public static String getUserIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");

        if (ip != null && !ip.equals("") && !"unKnown".equalsIgnoreCase(ip)) {
            int index = ip.replace(";", ",").indexOf(",");
            if (index != -1) {
                return ip.substring(0, index);
            } else {
                return ip;
            }
        }
        ip = request.getHeader("X-Real-IP");
        if (ip != null && !ip.equals("") && !"unKnown".equalsIgnoreCase(ip)) {
            return ip;
        }
        return request.getRemoteAddr();
    }

    public static Map<String, String> getAllIps(HttpServletRequest request, boolean printDebugInfo) {

        Map<String, String> map = new HashMap<>();

        Enumeration<String> attributeNames = request.getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            String name = attributeNames.nextElement();
            String val = request.getHeader(name);
            getDataFromCollect(map, name, val, printDebugInfo);
        }

        for (Map.Entry<String, String[]> stringEntry : request.getParameterMap().entrySet()) {
            getDataFromCollect(map, stringEntry.getKey(), stringEntry.getValue().toString(), printDebugInfo);
        }

        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String name = headerNames.nextElement();
            String val = request.getHeader(name);
            getDataFromCollect(map, name, val, printDebugInfo);

        }

        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String name = parameterNames.nextElement();
            String val = request.getParameter(name);
            getDataFromCollect(map, name, val, printDebugInfo);
        }
        return map;
    }

    private static void getDataFromCollect(Map<String, String> map, String name, String val, boolean debugInfo) {
        String regex = "\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(val);
        while (m.find()) {
            String group = m.group();
            map.put(group, name + ": " + val);

        }
        if (debugInfo) {
            System.out.println(name + ": " + val);
        }
    }

    public static String getUserAgent(HttpServletRequest request) {
        return request.getHeader("User-Agent");
    }

    public static void SetOrigin(ServletRequest request, ServletResponse response) {
        HttpServletResponse res = (HttpServletResponse) response;
        HttpServletRequest req = (HttpServletRequest) request;
        res.addHeader("Access-Control-Allow-Origin", "*");
        res.addHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS");
        res.addHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
    }

    public static String getToken(HttpServletRequest request) {

        String header = request.getHeader("Authorization");
        String accessToken = null;
        if (null != header) {
            String[] claims = header.split(" ");
            if (null != claims && claims.length > 1) {
                String i = claims[0];
                if ("Bearer".equals(i)) {
                    accessToken = claims[1];
                    return accessToken;
                }
            }
        }

        Cookie[] cookies = request.getCookies();
        if (null != cookies) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    accessToken = cookie.getValue();
                    return accessToken;
                    //System.out.println(LocalDateTime.now().toString() + " " + "Token from Cookie");
                }
            }
        }
        return "";
    }
}
