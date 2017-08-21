package com.ryan.shop.ec.launcher;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.ryan.shop.core.app.AccountManager;
import com.ryan.shop.core.app.IUserChecker;
import com.ryan.shop.core.delegate.RyanDelegate;
import com.ryan.shop.core.ui.launcher.ILauncherListener;
import com.ryan.shop.core.ui.launcher.LauncherHolderCreator;
import com.ryan.shop.core.ui.launcher.OnLauncherFinishTag;
import com.ryan.shop.core.ui.launcher.ScrollLauncherTag;
import com.ryan.shop.core.util.storage.RyanPreference;
import com.ryan.shop.ec.R;

import java.util.ArrayList;

/**
 * Created by zhuohf1 on 2017/8/17.
 */

public class LauncherScrollDelegate extends RyanDelegate implements OnItemClickListener {

    private ConvenientBanner<Integer> mConvenientBanner = null;
    private static final ArrayList<Integer> INTEGERS = new ArrayList<>();
    private ILauncherListener mILauncherListener = null;

    private void initBanner(){
        INTEGERS.clear();
        INTEGERS.add(R.drawable.launcher_02);
        INTEGERS.add(R.drawable.launcher_03);
        INTEGERS.add(R.drawable.launcher_04);
        INTEGERS.add(R.drawable.launcher_05);

        // setPageIndicator 这里的Indicator就是滑动时候下面的几个点
        mConvenientBanner.setPages(new LauncherHolderCreator(), INTEGERS)
                        .setPageIndicator(new int[]{R.drawable.dot_normal, R.drawable.dot_focus})
                        .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                        .setOnItemClickListener(this)
                        .setCanLoop(false);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ILauncherListener) {
            mILauncherListener = (ILauncherListener) activity;
        }
    }

    @Override
    public Object setLayout() {
        mConvenientBanner = new ConvenientBanner<>(getContext());
        return mConvenientBanner;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        initBanner();
    }

    @Override
    public void onItemClick(int position) {
        //如果点击的是最后一个
        if (position == INTEGERS.size() - 1) {
            RyanPreference.setAppFlag(ScrollLauncherTag.HAS_FIRST_LAUNCHER_APP.name(), true);
            //检查用户是否已经登录
            AccountManager.checkAccount(new IUserChecker() {
                @Override
                public void onSignIn() {
                    if (mILauncherListener != null) {
                        mILauncherListener.onLauncherFinish(OnLauncherFinishTag.SIGNED);
                    }
                }

                @Override
                public void onNotSignIn() {
                    if (mILauncherListener != null) {
                        mILauncherListener.onLauncherFinish(OnLauncherFinishTag.NOT_SIGNED);
                    }
                }
            });
        }
    }
}
