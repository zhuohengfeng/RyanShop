package com.ryan.shop.core.app;

import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by zhuohf1 on 2017/8/4.
 */

public final class Configurator {
    private static final HashMap<Object, Object> RYAN_CONFIGS = new HashMap<>();
    private static final ArrayList<IconFontDescriptor> ICONS = new ArrayList<>();

    private Configurator() {
        RYAN_CONFIGS.put(ConfigKeys.CONFIG_READY, false);
        //RYAN_CONFIGS.put(ConfigKeys.HANDLER, HANDLER);
    }

    static Configurator getInstance() {
        return Holder.INSTANCE;
    }

    private static class Holder {
        private static final Configurator INSTANCE = new Configurator();
    }

    final HashMap<Object, Object> getRyanConfigs() {
        return RYAN_CONFIGS;
    }


    public final Configurator withApiHost(String host) {
        RYAN_CONFIGS.put(ConfigKeys.API_HOST, host);
        return this;
    }

    // 初始化字体库
    private void initIcons() {
        if (ICONS.size() > 0) { // 如果已经有字体了
            final Iconify.IconifyInitializer initializer = Iconify.with(ICONS.get(0));
            for (int i = 1; i < ICONS.size(); i++) {
                initializer.with(ICONS.get(i));
            }
        }
    }

    // 加入自己的字体图标
    public final Configurator withIcon(IconFontDescriptor descriptor) {
        ICONS.add(descriptor);
        return this;
    }

    public final void configComplete() {
        initIcons();
        //Logger.addLogAdapter(new AndroidLogAdapter());
        RYAN_CONFIGS.put(ConfigKeys.CONFIG_READY, true);
        //Utils.init(Latte.getApplicationContext());
    }

    //
    private void checkConfiguration() {
        final boolean isReady = (boolean) RYAN_CONFIGS.get(ConfigKeys.CONFIG_READY);
        if (!isReady) {
            throw new RuntimeException("Configuration is not ready,call configure");
        }
    }

    @SuppressWarnings("unchecked")
    final <T> T getConfiguration(Object key) {
        checkConfiguration();
        final Object value = RYAN_CONFIGS.get(key);
        if (value == null) {
            throw new NullPointerException(key.toString() + " IS NULL");
        }
        return (T) RYAN_CONFIGS.get(key);
    }
}
