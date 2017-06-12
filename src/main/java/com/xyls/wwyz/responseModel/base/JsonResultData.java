package com.xyls.wwyz.responseModel.base;

/**
 * Created by Lee on 2017/6/12.
 */
public class JsonResultData<T> extends JsonResult {

    public JsonResultData() {
    }

    public JsonResultData(T data) {
        this.flag = true;
        this.state = 1;
        this.data = data;
    }

    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
