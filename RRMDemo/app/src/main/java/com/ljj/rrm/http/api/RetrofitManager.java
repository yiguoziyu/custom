package com.ljj.rrm.http.api;

import android.content.Context;
import android.util.Log;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.ljj.rrm.http.RetrofitService;
import com.ljj.rrm.http.ServiceConfig;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

//import org.simpleframework.xml.Serializer;
//import org.simpleframework.xml.convert.AnnotationStrategy;
//import org.simpleframework.xml.core.Persister;
//import org.simpleframework.xml.strategy.Strategy;
//import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

/**
 * Created by 1 on 2017/9/29.
 */

public class RetrofitManager {

    private static RetrofitManager retrofitManager;
    private RetrofitService retrofitService;

//    private static Strategy strategy = new AnnotationStrategy();
//    private static Serializer serializer = new Persister(strategy);

    private RetrofitManager(Context context) {
        OkHttpClient okHttpClient = initOkHttpClient(context);
        Retrofit retrofit = initRetrofit(okHttpClient);
        retrofitService = retrofit.create(RetrofitService.class);
    }

    public static RetrofitManager getInstance(Context context) {
        if (retrofitManager == null) {
            synchronized (RetrofitManager.class) {
                if (retrofitManager == null) {
                    retrofitManager = new RetrofitManager(context);
                }
            }
        }
        return retrofitManager;
    }

    public RetrofitService getRetrofitService() {
        return retrofitService;
    }

    /**
     * 初始化okhttp
     *
     * @param context
     * @return
     */
    private OkHttpClient initOkHttpClient(Context context) {
        //新建一个文件用来缓存网络请求
        File cacheDirectory = new File(context.getCacheDir().getAbsolutePath() + "responses");
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(ServiceConfig.TIMEOUT, TimeUnit.SECONDS)//设置连接超时
                .readTimeout(ServiceConfig.TIMEOUT, TimeUnit.SECONDS)//设置从主机读信息超时
                .writeTimeout(ServiceConfig.TIMEOUT, TimeUnit.SECONDS)//设置写信息超时
                .retryOnConnectionFailure(true)///如果连接失败,尝试重连
                .cache(new Cache(cacheDirectory, 10 * 1024 * 1024))//设置缓存文件
//                .addInterceptor(initInterceptor())//设置okhttp拦截器，这样做的好处是可以为你的每一个retrofit2的网络请求都增加相同的head头信息，而不用每一个请求都写头信息
                .addInterceptor(initLogInterceptor())//打印日志
                .build();
        return okHttpClient;
    }

    /**
     * 初始化http拦截器
     *
     * @return
     */
    private Interceptor initInterceptor() {
        BasicParamsInterceptor builder = new BasicParamsInterceptor.Builder()
                .addHeaderParam("Content-Type", "text/xml; charset=utf-8")// 对于SOAP 1.1， 如果是soap1.2 应是Content-Type: application/soap+xml; charset=utf-8
                .build();
        return builder;
    }

    /**
     * 初始化日志信息
     *
     * @return
     */
    private Interceptor initLogInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.i("TTT", "请求参数:" + message);
            }
        });
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return interceptor;
    }

    /***
     * 初始化Retrofit
     */
    private Retrofit initRetrofit(OkHttpClient client) {
        Retrofit retrofit = new Retrofit.Builder()
                .client(client)//设置 HTTP Client  用于请求的连接
//                .addConverterFactory(SimpleXmlConverterFactory.create(serializer))//解析xml
                .addConverterFactory(ScalarsConverterFactory.create())//如果网络访问返回的字符串，而不是json数据格式，要使用下面的转换器
                .addConverterFactory(GsonConverterFactory.create())//如果网络访问返回的是json字符串，使用gson转换器
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//此处顺序不能和上面对调，否则不能同时兼容普通字符串和Json格式字符串  为了支持rxjava,需要添加下面这个把 Retrofit 转成RxJava可用的适配类
                .baseUrl(ServiceConfig.BASE_URL)
                .build();
        return retrofit;
    }

}
