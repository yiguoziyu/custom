package com.ljj.rrm.account.presenter;


import com.ljj.rrm.http.api.IPresenter;


/**
 * 用户主持接口
 * Created by 1 on 2018/1/24.
 */

public interface IAccountPresenter extends IPresenter {
    /**
     * 登入
     *
     * @param userName
     * @param password
     */
    void login(String userName, String password);


}
