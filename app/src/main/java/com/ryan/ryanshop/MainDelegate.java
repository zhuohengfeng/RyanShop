package com.ryan.ryanshop;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.ryan.shop.core.delegate.RyanDelegate;

/**
 * Created by zhuohf1 on 2017/8/5.
 */

public class MainDelegate extends RyanDelegate {

    @Override
    public Object setLayout() {
        return R.layout.delegate_main;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        //
    }
}
