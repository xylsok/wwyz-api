package com.xyls.wwyz.ui;

import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Lee on 2017/6/12.
 */
public class BaseWeb {

    HttpServletRequest request;
    HttpServletResponse response;
    @ModelAttribute
    public void init(HttpServletRequest request, HttpServletResponse response){
        this.request = request;
        this.response = response;
    }
}
