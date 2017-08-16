package com.ryan.ryanshop;

import com.ryan.shop.core.activity.ProxyActivity;
import com.ryan.shop.core.delegate.RyanDelegate;

public class MainActivity extends ProxyActivity {

    @Override
    public RyanDelegate setRootDelegate() {
        return new MainDelegate();
    }

}
