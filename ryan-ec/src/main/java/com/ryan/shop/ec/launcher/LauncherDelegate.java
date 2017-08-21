package com.ryan.shop.ec.launcher;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.ryan.shop.core.app.AccountManager;
import com.ryan.shop.core.app.IUserChecker;
import com.ryan.shop.core.delegate.RyanDelegate;
import com.ryan.shop.core.ui.launcher.ILauncherListener;
import com.ryan.shop.core.ui.launcher.OnLauncherFinishTag;
import com.ryan.shop.core.ui.launcher.ScrollLauncherTag;
import com.ryan.shop.core.util.storage.RyanPreference;
import com.ryan.shop.core.util.timer.BaseTimerTask;
import com.ryan.shop.core.util.timer.ITimerListener;
import com.ryan.shop.ec.R;

import java.text.MessageFormat;
import java.util.Timer;

/**
 * Created by zhuohf1 on 2017/8/17.
 */
// 创建启动页面，需要创建一个timer
public class LauncherDelegate extends RyanDelegate implements ITimerListener{

    //@BindView(R2.id.tv_timer_launcher)
    private AppCompatTextView mTvTimer = null;

    private Timer mTimer = null;
    private int mCount = 5;
    private ILauncherListener mILauncherListener = null;

    @Override
    public Object setLayout() {
        return R.layout.delegate_launcher;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        mTvTimer = rootView.findViewById(R.id.tv_timer_launcher);
        mTvTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if( mTimer != null) {
                    mTimer.cancel();
                    mTimer = null;
                    checkIsShowScroll();
                }
            }
        });

        initTimer();
    }

    private void initTimer(){
        mTimer = new Timer();
        final BaseTimerTask tast = new BaseTimerTask(this);

        mTimer.schedule(tast, 0, 1000);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ILauncherListener) {
            mILauncherListener = (ILauncherListener) activity;
        }
    }


    //判断是否显示滑动启动页
    private void checkIsShowScroll() {
        if (!RyanPreference.getAppFlag(ScrollLauncherTag.HAS_FIRST_LAUNCHER_APP.name())) {
            getSupportDelegate().start(new LauncherScrollDelegate(), SINGLETASK);
        } else {
            //检查用户是否登录了APP
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

    @Override
    public void onTimer() {
        getProxyActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mTvTimer != null) {
                    mTvTimer.setText(MessageFormat.format("跳过\n{0}s", mCount));
                    mCount--;
                    if (mCount < 0) {
                        if (mTimer != null) {
                            mTimer.cancel();
                            mTimer = null;
                            checkIsShowScroll();
                        }
                    }
                }
            }
        });
    }
}
