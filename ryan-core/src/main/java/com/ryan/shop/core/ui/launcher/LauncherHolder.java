package com.ryan.shop.core.ui.launcher;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;

import com.bigkoo.convenientbanner.holder.Holder;

/**
 * Created by zhuohf1 on 2017/8/17.
 */

public class LauncherHolder implements Holder<Integer> {

    private AppCompatImageView mLauncherImageView = null;

    @Override
    public View createView(Context context) {
        mLauncherImageView = new AppCompatImageView(context);
        return mLauncherImageView;
    }

    @Override
    public void UpdateUI(Context context, int position, Integer data) {
        mLauncherImageView.setBackgroundResource(data);
    }

}
