package com.ljj.rrm.base;


/**
 * Created by 1 on 2018/1/24.
 */

public interface IView {
    /**
     * 显示进度
     */
    void showProgress(int code, boolean ishow);

    /**
     * 显示错误
     */
    void showError(int code, String result);

}
