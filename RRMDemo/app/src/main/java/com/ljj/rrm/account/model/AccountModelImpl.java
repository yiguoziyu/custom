package com.ljj.rrm.account.model;


import com.ljj.rrm.http.HttpInterfaceCode;
import com.ljj.rrm.http.RetrofitService;
import com.ljj.rrm.http.api.IPresenter;
import com.ljj.rrm.http.api.IRxSubscribler;
import com.ljj.rrm.http.api.RetrofitManager;
import com.ljj.rrm.http.api.RxFlowableTransformer;

/**
 * 用户具体实现类
 * Created by 1 on 2018/1/24.
 */

public class AccountModelImpl implements IAccountModel {
    private RetrofitService rService;
    private IPresenter basePresenter;

    public AccountModelImpl(RetrofitManager retrofitManager, IPresenter basePresenter) {
        rService = retrofitManager.getRetrofitService();
        this.basePresenter = basePresenter;
    }

    @Override
    public void login(String userName, String password) {
        rService.login(userName, password)
                .compose(new RxFlowableTransformer())
                .subscribe(new IRxSubscribler(basePresenter, HttpInterfaceCode.LOGIN_SUC));
    }

}
