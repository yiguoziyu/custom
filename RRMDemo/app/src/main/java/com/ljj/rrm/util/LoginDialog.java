package com.ljj.rrm.util;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.ljj.rrm.R;


/**
 * Created by 1 on 2018/2/1.
 */

public class LoginDialog extends DialogFragment {
    public static final String DIALOG_CONTENT = "content";
    private CountDownTimer countDownTimer;
    private static LoginDialog loginDialog;

    public static LoginDialog getInstance(Activity activity,String hint){
        if (loginDialog == null) {
            loginDialog = new LoginDialog();
        }
        Bundle bundle = new Bundle();
        bundle.putString(LoginDialog.DIALOG_CONTENT, hint);
        loginDialog.setArguments(bundle);
        loginDialog.show(activity.getFragmentManager(), "loginDialog");
        return loginDialog;
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_login, null);
        TextView tv = view.findViewById(R.id.login_dia_tv);
        String content = (String) getArguments().get(DIALOG_CONTENT);
        if (!TextUtils.isEmpty(content)) {
            tv.setText(content);
        }
        builder.setView(view);
        AlertDialog dialog = builder.create();
        Window window = dialog.getWindow();
        window.setBackgroundDrawableResource(android.R.color.transparent);
        return dialog;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        countDownTimer = new CountDownTimer(5000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                if (millisUntilFinished / 1000 > 5) {
                    onFinish();
                }
            }

            @Override
            public void onFinish() {
                dismiss();
            }
        }.start();
    }


    @Override
    public void onStart() {
        super.onStart();
        Window window = getDialog().getWindow();
        WindowManager.LayoutParams windowParams = window.getAttributes();
        windowParams.dimAmount = 0.0f;
        window.setAttributes(windowParams);
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        countDownTimer.cancel();
    }
}
