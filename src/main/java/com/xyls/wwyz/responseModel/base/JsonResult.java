package com.xyls.wwyz.responseModel.base;

/**
 * Created by Lee on 2017/6/12.
 */
public class JsonResult {
    public JsonResult() {
    }

    boolean flag;
    int state;

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
