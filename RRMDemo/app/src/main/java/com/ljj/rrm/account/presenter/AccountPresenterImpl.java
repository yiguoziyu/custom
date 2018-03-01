package com.ljj.rrm.account.presenter;



import com.ljj.rrm.account.model.AccountModelImpl;
import com.ljj.rrm.account.model.IAccountModel;
import com.ljj.rrm.account.view.ILoginView;
import com.ljj.rrm.http.HttpInterfaceCode;
import com.ljj.rrm.http.api.RetrofitManager;


/**
 * 用户主持实现类
 * Created by 1 on 2018/1/24.
 */

public class AccountPresenterImpl implements IAccountPresenter {
    private IAccountModel iAccountModel;
    private ILoginView iLoginView;

    public AccountPresenterImpl(ILoginView iLoginView, RetrofitManager retrofitManager) {
        this.iLoginView = iLoginView;
        iAccountModel = new AccountModelImpl(retrofitManager, this);
    }


    @Override
    public void login(String userName, String password) {
        iAccountModel.login(userName, password);
    }




    @Override
    public void success(int code, Object result) {
        switch (code) {
            case HttpInterfaceCode.LOGIN_SUC:
                iLoginView.loginSuc(HttpInterfaceCode.LOGIN_SUC, result);
                break;
        }
    }

    @Override
    public void error(int code, String error) {
        switch (code) {
            case HttpInterfaceCode.LOGIN_FAI:
                iLoginView.showError(HttpInterfaceCode.LOGIN_FAI, error);
                break;
        }
    }
}
