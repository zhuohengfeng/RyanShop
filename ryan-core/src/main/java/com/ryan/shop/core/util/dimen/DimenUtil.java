package com.ryan.shop.core.util.dimen;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.ryan.shop.core.app.Ryan;

/**
 * Created by zhuohf1 on 2017/8/16.
 */

public final class DimenUtil {

    public static int getScreenWidth() {
        final Resources resources = Ryan.getApplicationContext().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.widthPixels;
    }

    public static int getScreenHeight() {
        final Resources resources = Ryan.getApplicationContext().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.heightPixels;
    }
}
