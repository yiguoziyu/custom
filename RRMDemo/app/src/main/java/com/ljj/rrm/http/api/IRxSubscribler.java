package com.ljj.rrm.http.api;


/**
 * Created by 1 on 2018/1/29.
 */

public class IRxSubscribler<T> extends RxSubscribler<T> {
    private IPresenter basePresenter;
    private int interfaceCode;

    public IRxSubscribler(IPresenter basePresenter, int interfaceCode) {
        this.basePresenter = basePresenter;
        this.interfaceCode = interfaceCode;
    }

    @Override
    public void success(Object o) {
        basePresenter.success(interfaceCode, o);
    }

    @Override
    public void error(String result) {
        basePresenter.error(-interfaceCode, result);
    }
}
