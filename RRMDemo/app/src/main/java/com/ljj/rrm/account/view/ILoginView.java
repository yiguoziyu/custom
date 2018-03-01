package com.ljj.rrm.account.view;


import com.ljj.rrm.base.IView;

/**
 * Created by 1 on 2018/1/24.
 */

public interface ILoginView extends IView {
    /**
     * 登入成功
     *
     * @param code
     * @param result
     */
    void loginSuc(int code, Object result);
}
