package com.ljj.rrm.http.api;

/**
 * Created by 1 on 2018/2/8.
 */

public class BaseResponse {
    private int code;
    private Object data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
