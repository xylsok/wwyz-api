package com.xyls.wwyz.responseModel.base;

import java.util.Map;

/**
 * Created by Lee on 2017/6/12.
 */
public class JsonResultError extends JsonResult {

    public JsonResultError() {
    }

    public JsonResultError(String msg) {
        this.flag = false;
        this.state = 0;
        this.code = 1;
        this.msg = msg;
    }

    public JsonResultError(int code, String msg) {
        this.flag = false;
        this.state = 0;
        this.code = code;
        this.msg = msg;
    }

    public JsonResultError(int code, Map<String, String> msgMap) {
        this.flag = false;
        this.state = 0;
        this.code = code;
        this.msgMap = msgMap;
    }

    private int code;
    private String msg;
    private Map<String,String> msgMap;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Map<String, String> getMsgMap() {
        return msgMap;
    }

    public void setMsgMap(Map<String, String> msgMap) {
        this.msgMap = msgMap;
    }
}
