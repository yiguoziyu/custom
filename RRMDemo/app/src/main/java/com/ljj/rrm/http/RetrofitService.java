package com.ljj.rrm.http;


import com.ljj.rrm.http.api.BaseResponse;

import io.reactivex.Flowable;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by 1 on 2018/1/24.
 */

public interface RetrofitService {

    @POST(ServiceConfig.LOGIN)
    Flowable<BaseResponse> login(@Query("username") String userName, @Query("password") String passWord);


}
