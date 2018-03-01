package com.ljj.rrm.base;


import android.os.Bundle;
import android.support.annotation.Nullable;

import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.Utils;
import me.imid.swipebacklayout.lib.app.SwipeBackActivityBase;
import me.imid.swipebacklayout.lib.app.SwipeBackActivityHelper;

/**
 * Activity基类
 * Created by 1 on 2017/10/12.
 */

public abstract class BaseActivity extends IActivity implements SwipeBackActivityBase {
    //侧滑返回
    public SwipeBackActivityHelper mHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        //初始化侧滑返回
        mHelper = new SwipeBackActivityHelper(this);
        mHelper.onActivityCreate();
        initSwipeBackLayout();
        super.onCreate(savedInstanceState);
    }

    /**
     * 侧滑返回API
     *
     * @param savedInstanceState
     */
    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mHelper.onPostCreate();
    }

    @Override
    public SwipeBackLayout getSwipeBackLayout() {
        return mHelper.getSwipeBackLayout();
    }

    @Override
    public void setSwipeBackEnable(boolean enable) {
        getSwipeBackLayout().setEnableGesture(enable);
    }

    @Override
    public void scrollToFinishActivity() {
        Utils.convertActivityToTranslucent(this);
        getSwipeBackLayout().scrollToFinishActivity();
    }

    /**
     * 默认设置左滑回退
     */
    private void initSwipeBackLayout() {
        if (getSwipeBackLayout() != null) {
            getSwipeBackLayout().setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);
        }
    }


}
