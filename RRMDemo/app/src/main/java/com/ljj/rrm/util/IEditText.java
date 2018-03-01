package com.ljj.rrm.util;

import android.content.Context;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;


/**
 * 简易EditText
 * Created by 1 on 2018/1/15.
 */

public class IEditText extends AppCompatEditText {
    private int editTag = 0;

    public interface TextChangedListener {
        void onTextChanged(String data, int tag);
    }

    private TextChangedListener changedListener;

    public IEditText(Context context) {
        this(context, null);
    }

    public IEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (changedListener != null) {
                    changedListener.onTextChanged(getInputResult(),editTag);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }

    public void setChangedListener(TextChangedListener changedListener) {
        this.changedListener = changedListener;
    }

    public void setChangedListener(TextChangedListener changedListener, int editTag) {
        this.changedListener = changedListener;
        this.editTag = editTag;
    }


    /**
     * 获取值
     *
     * @return
     */
    public String getInputResult() {
        return getText().toString().trim();
    }

    /**
     * 验证是否为手机号
     */
    public boolean checkPhoneNumber() {
        String number = getInputResult();
        String num = "[1][34578]\\d{9}";//"[1]"代表第1位为数字1，"[34578]"代表第二位可以为3、4、5、7、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (TextUtils.isEmpty(number)) {
            return false;
        } else {
            //matches():字符串是否在给定的正则表达式匹配
            return number.matches(num);
        }
    }

    /**
     * 验证是否为空
     *
     * @param tip
     */
    public void checkInputNull(String tip) {
        String eResult = getInputResult();
        if (TextUtils.isEmpty(eResult)) {
            throw new NullPointerException(tip);
        }
    }


}
