package com.ljj.rrm.http.api;

import org.reactivestreams.Publisher;

import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by lijiajun on 2018/1/27.
 */

public final class RxFlowableTransformer implements FlowableTransformer {
    @Override
    public Publisher apply(Flowable upstream) {
        return upstream.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
