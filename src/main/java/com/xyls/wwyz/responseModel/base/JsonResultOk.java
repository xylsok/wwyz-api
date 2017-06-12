package com.xyls.wwyz.responseModel.base;

/**
 * Created by Lee on 2017/6/12.
 */
public class JsonResultOk extends JsonResult {

    public JsonResultOk() {
    }

    public JsonResultOk(String message) {
        this.flag = true;
        this.state = 1;
        this.message = message;
    }

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
