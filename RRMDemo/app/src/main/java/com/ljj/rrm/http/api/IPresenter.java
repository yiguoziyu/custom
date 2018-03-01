package com.ljj.rrm.http.api;


/**
 * Created by 1 on 2018/1/29.
 */

public interface IPresenter {
    void success(int code, Object result);

    void error(int code, String result);
}
