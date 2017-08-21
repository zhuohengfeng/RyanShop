package com.ryan.shop.core.app;

import android.content.Context;
import android.os.Handler;

/**
 * Created by zhuohf1 on 2017/8/4.
 */
// final类型，不允许继承
public final class Ryan {

    public static Configurator init(Context context) {
        Configurator.getInstance()
                .getRyanConfigs()
                .put(ConfigKeys.APPLICATION_CONTEXT, context.getApplicationContext());
        return Configurator.getInstance();
    }

    public static Configurator getConfigurator() {
        return Configurator.getInstance();
    }

    public static <T> T getConfiguration(Object key) {
        return getConfigurator().getConfiguration(key);
    }

    public static Context getApplicationContext() {
        return getConfiguration(ConfigKeys.APPLICATION_CONTEXT);
    }

    public static Handler getHandler() {
        return getConfiguration(ConfigKeys.HANDLER);
    }

    public static void test(){
    }
}
