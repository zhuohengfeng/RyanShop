package com.ryan.shop.core.app;

/**
 * Created by zhuohf1 on 2017/8/21.
 */

public interface IUserChecker {
    // 有用户信息
    void onSignIn();
    // 没有用户信息
    void onNotSignIn();
}
