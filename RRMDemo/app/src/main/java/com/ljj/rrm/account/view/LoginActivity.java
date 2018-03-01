package com.ljj.rrm.account.view;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ljj.rrm.MainActivity;
import com.ljj.rrm.R;
import com.ljj.rrm.account.presenter.AccountPresenterImpl;
import com.ljj.rrm.account.presenter.IAccountPresenter;
import com.ljj.rrm.base.IActivity;
import com.ljj.rrm.http.HttpInterfaceCode;
import com.ljj.rrm.http.api.RetrofitManager;
import com.ljj.rrm.util.IEditText;
import com.ljj.rrm.util.IToolbar;
import com.ljj.rrm.util.LoginDialog;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 1 on 2018/1/24.
 */

public class LoginActivity extends IActivity implements ILoginView, IEditText.TextChangedListener {
    @BindView(R.id.us_idt)
    IEditText usIdt;
    @BindView(R.id.us_de)
    ImageView usDe;
    @BindView(R.id.pw_idt)
    IEditText pwIdt;
    @BindView(R.id.pw_de)
    ImageView pwDe;
    @BindView(R.id.to_register)
    TextView toRegister;
    @BindView(R.id.to_forget_pw)
    TextView toForgetPw;
    @BindView(R.id.login)
    Button login;
    @BindView(R.id.custom_itoobar)
    IToolbar customItoobar;
    private IAccountPresenter iAPresenter;
    private LoginDialog loginDialog;
    public static final int USIDT = 1;
    public static final int PWIDT = 2;

    //--------------------------------------------基础设置----------------------------------------//
    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initData() {
        customItoobar.setTitle(R.string.login);
        initPresenter();
    }


    @Override
    protected void initListener() {
        usIdt.setChangedListener(this,USIDT);
        pwIdt.setChangedListener(this,PWIDT);
    }

    @OnClick({R.id.us_de, R.id.pw_de, R.id.to_register, R.id.to_forget_pw, R.id.login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.us_de:
                usIdt.setText("");
                break;
            case R.id.pw_de:
                pwIdt.setText("");
                break;
            case R.id.to_register:
                toRegisterOrForgetPwView(true);
                break;
            case R.id.to_forget_pw:
                toRegisterOrForgetPwView(false);
                break;
            case R.id.login:
                login();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (loginDialog != null) {
            loginDialog.onDestroy();
        }

    }

    //--------------------------------------------接口回调----------------------------------------//
    @Override
    public void onTextChanged(String data, int tag) {
        int isVis = TextUtils.isEmpty(data) ? View.GONE : View.VISIBLE;
        switch (tag) {
            case USIDT:
                usDe.setVisibility(isVis);
                break;
            case PWIDT:
                pwDe.setVisibility(isVis);
                break;
        }
    }

    @Override
    public void showProgress(int code, boolean ishow) {
        switch (code) {
            case HttpInterfaceCode.LOGIN_SUC:
                break;
        }
    }

    @Override
    public void showError(int code, String result) {
        switch (code) {
            case HttpInterfaceCode.LOGIN_FAI:
                break;
        }
    }

    @Override
    public void loginSuc(int code, Object result) {
        //存到本地
        toMainView();
    }

    //--------------------------------------------初始化----------------------------------------//

    /**
     * 初始化Presenter
     */
    private void initPresenter() {
        RetrofitManager retrofitManager = RetrofitManager.getInstance(context);
        iAPresenter = new AccountPresenterImpl(this, retrofitManager);
    }

    /**
     * 登入主页
     */
    private void toMainView() {
        Intent intent = new Intent(context, MainActivity.class);
        startActivity(intent);
        finish();
    }

    /**
     * 去注册或忘记密码页面
     */
    private void toRegisterOrForgetPwView(boolean isRegister) {

    }


    /**
     * 登入
     */
    private void login() {
        try {
            usIdt.checkInputNull(getResources().getString(R.string.us_not_null));
            pwIdt.checkInputNull(getResources().getString(R.string.pw_not_null));
        } catch (NullPointerException e) {
            showHintDialog(e.getMessage());
            return;
        }
        iAPresenter.login(usIdt.getInputResult(), pwIdt.getInputResult());
    }

    /**
     * 提示框
     */
    private void showHintDialog(String hint) {
        loginDialog = loginDialog.getInstance(this, hint);
    }


}
