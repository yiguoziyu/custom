package com.ljj.rrm.http;

/**
 * 接口code
 * Created by 1 on 2018/1/24.
 */

public class HttpInterfaceCode {
    //请求成功
    public static final int NET_REUQEST_OK = 200;
    //请求错误
    public static final int NET_REQUEST_ERROR = 400;
    //访问被拒绝
    public static final int NET_REQUEST_REJECT = 401;
    //未找到
    public static final int NET_REQUEST_UNFOUND = 404;
    //内部服务器错误
    public static final int NET_SERVICE_ERROR = 500;
    //未知错误
    public static final int NET_NUKNOWN_ERROR = 900;

    public static final int LOGIN_SUC = 101;
    public static final int LOGIN_FAI = -101;
    public static final int REGISTER_SUC = 102;
    public static final int REGISTER_FAI = -102;
    public static final int FORGET_PW_SUC = 103;
    public static final int FORGET_PW_FAI = -103;
}
