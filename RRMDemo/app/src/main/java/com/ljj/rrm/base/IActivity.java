package com.ljj.rrm.base;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.ljj.rrm.util.SystemBarTintManager;
import com.trello.rxlifecycle2.components.support.RxFragmentActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Activity基类
 * Created by 1 on 2017/10/12.
 */

public abstract class IActivity extends RxFragmentActivity {
    public Unbinder bind;
    public Context context;
    //沉浸式管理器
    public SystemBarTintManager tintManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //初始化布局内容
        setContentView(getLayoutId());
        context = this;
        //初始化ButterKnife
        bind = ButterKnife.bind(this);
        //初始化沉浸式
        initSystemBarTinit();
        //内存监测
        initLeakcanary();
        //初始化数据
        initData();
        //初始化监听
        initListener();
    }

    /**
     * 设置布局layout
     *
     * @return
     */
    protected abstract int getLayoutId();

    /**
     * 初始化数据
     */
    protected abstract void initData();

    /***
     * 初始化监听
     */
    protected abstract void initListener();


    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }

    /**
     * 初始化沉浸式
     */
    private void initSystemBarTinit() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
            tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setStatusBarTintColor(Color.parseColor("#00000000"));
        }
    }

    /**
     * 内存监测leakcanary
     */
    private void initLeakcanary() {
//        RefWatcher refWatcher = MyApplication.getRefWatcher(context);
//        refWatcher.watch(this);
    }

    /**
     * 设置状态栏透明状态
     */
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    /**
     * 设置toolbar位置
     */
    public void setTitleBar(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            int width = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
            int height = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
            view.measure(width, height);
            view.setPadding(view.getPaddingLeft(), view.getPaddingTop() + tintManager.getNavigationBarHeight(),
                    view.getPaddingRight(), view.getPaddingBottom());
        }
    }



}
