package com.ryan.shop.core.net.callback;

/**
 * Created by zhuohf1 on 2017/8/16.
 */

public interface IError {

    void onError(int code, String msg);
}