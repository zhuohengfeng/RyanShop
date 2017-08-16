package com.ryan.shop.core.delegate;

/**
 * Created by zhuohf1 on 2017/8/5.
 */

public abstract class RyanDelegate extends PermisstionCheckerDelegate {

    @SuppressWarnings("unchecked")
    public <T extends RyanDelegate> T getParentDelegate() {
        return (T) getParentFragment();
    }
}

