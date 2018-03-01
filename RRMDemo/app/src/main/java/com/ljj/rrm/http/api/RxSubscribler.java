package com.ljj.rrm.http.api;



import com.ljj.rrm.http.HttpInterfaceCode;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeoutException;

/**
 * Created by lijiajun on 2018/1/27.
 */

public abstract class RxSubscribler<T> implements Subscriber<T> {
    @Override
    public final void onSubscribe(Subscription s) {
        s.request(Long.MAX_VALUE);
    }

    @Override
    public final void onNext(T o) {
        checkIsSucc(o);
    }

    @Override
    public final void onError(Throwable t) {

        if (t instanceof SocketTimeoutException) {
            error("服务器连接超时");
        } else if (t instanceof ConnectException) {
            error("服务器链接失败");
        } else if (t instanceof TimeoutException) {
            error("网络超时");
        } else if (t instanceof UnknownHostException) {
            error("未知主机错误");
        } else {
            error("未知错误");
            t.printStackTrace();
        }
    }

    @Override
    public final void onComplete() {

    }

    public abstract void success(T o);

    public abstract void error(String result);

    private void checkIsSucc(T o) {
        if (o instanceof BaseResponse) {
            BaseResponse response = (BaseResponse) o;
            String errorMessage = "未知错误";
            switch (response.getCode()) {
                case HttpInterfaceCode.NET_REUQEST_OK:
                    success(o);
                    return;
                case HttpInterfaceCode.NET_REQUEST_ERROR:
                    errorMessage = "请求错误";
                    break;
                case HttpInterfaceCode.NET_REQUEST_REJECT:
                    errorMessage = "请求被拒绝";
                    break;
                case HttpInterfaceCode.NET_REQUEST_UNFOUND:
                    errorMessage = "请求未响应";
                    break;
                case HttpInterfaceCode.NET_SERVICE_ERROR:
                    errorMessage = "服务器错误";
                    break;
                case HttpInterfaceCode.NET_NUKNOWN_ERROR:
                    errorMessage = "未知错误";
                    break;
            }
            error(errorMessage);
        }

    }
}
