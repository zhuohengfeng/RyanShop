package com.ryan.ryanshop;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.ryan.shop.core.app.Ryan;
import com.ryan.shop.ec.database.DatabaseManager;
import com.ryan.shop.ec.icon.FontEcModule;

/**
 * Created by zhuohf1 on 2017/8/4.
 */

public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Ryan.init(this)
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontEcModule())
                .withLoaderDelayed(1000)
                .withApiHost("196.168.1.1")
                //.withInterceptor(new DebugInterceptor("test", R.raw.test))
                //.withWeChatAppId("你的微信AppKey")
                //.withWeChatAppSecret("你的微信AppSecret")
                //.withJavascriptInterface("latte")
                //.withWebEvent("test", new TestEvent())
                //.withWebEvent("share", new ShareEvent())
                .configComplete();

        initStetho();

        // 初始数据库
        DatabaseManager.getInstance().init(this);
    }

    private void initStetho() {
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                        .build());
    }
}
