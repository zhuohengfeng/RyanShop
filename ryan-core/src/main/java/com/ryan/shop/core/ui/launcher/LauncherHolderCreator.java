package com.ryan.shop.core.ui.launcher;

import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;

/**
 * Created by zhuohf1 on 2017/8/17.
 */

public class LauncherHolderCreator implements CBViewHolderCreator<LauncherHolder> {


    @Override
    public LauncherHolder createHolder() {
        return new LauncherHolder();
    }
}
