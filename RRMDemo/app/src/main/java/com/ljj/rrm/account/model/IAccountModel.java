package com.ljj.rrm.account.model;



/**
 * 用户接口Model类
 * Created by 1 on 2018/1/24.
 */

public interface IAccountModel {

    /**
     * 登入
     *
     * @param userName
     * @param password
     */
    void login(String userName, String password);

}
