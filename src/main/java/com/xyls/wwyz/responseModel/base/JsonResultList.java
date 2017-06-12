package com.xyls.wwyz.responseModel.base;

import java.util.List;

/**
 * Created by Lee on 2017/6/12.
 */
public class JsonResultList<T> extends JsonResult {


    public JsonResultList() {
    }

    public JsonResultList(List<T> list) {
        this.flag = true;
        this.state = 1;
        this.list = list;
    }

    private List<T> list;

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
