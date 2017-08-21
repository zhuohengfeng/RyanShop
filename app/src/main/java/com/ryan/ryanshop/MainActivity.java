package com.ryan.ryanshop;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.widget.Toast;

import com.ryan.shop.core.activity.ProxyActivity;
import com.ryan.shop.core.app.Ryan;
import com.ryan.shop.core.delegate.RyanDelegate;
import com.ryan.shop.core.ui.launcher.ILauncherListener;
import com.ryan.shop.core.ui.launcher.OnLauncherFinishTag;
import com.ryan.shop.ec.launcher.LauncherDelegate;
import com.ryan.shop.ec.sign.ISignListener;
import com.ryan.shop.ec.sign.SignInDelegate;

public class MainActivity extends ProxyActivity implements
        ISignListener, ILauncherListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        Ryan.getConfigurator().withActivity(this);
        StatusBarCompat.translucentStatusBar(this, true);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //JPushInterface.onPause(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //JPushInterface.onResume(this);
    }

    @Override
    public RyanDelegate setRootDelegate() {
        return new LauncherDelegate();//new EcBottomDelegate();
    }

    @Override
    public void onSignInSuccess() {
        Toast.makeText(this, "登录成功", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onSignUpSuccess() {
        Toast.makeText(this, "注册成功", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onLauncherFinish(OnLauncherFinishTag tag) {
        switch (tag) {
            case SIGNED:
//                Toast.makeText(this, "启动结束，用户登录了", Toast.LENGTH_LONG).show();
                getSupportDelegate().startWithPop(new EcBottomDelegate());
                break;
            case NOT_SIGNED:
//                Toast.makeText(this, "启动结束，用户没登录", Toast.LENGTH_LONG).show();
                getSupportDelegate().startWithPop(new SignInDelegate());
                break;
            default:
                break;
        }
    }

}
