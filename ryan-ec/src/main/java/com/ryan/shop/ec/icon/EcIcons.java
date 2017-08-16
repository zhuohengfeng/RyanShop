package com.ryan.shop.ec.icon;
import com.joanzapata.iconify.Icon;
/**
 * Created by zhuohf1 on 2017/8/5.
 */

public enum EcIcons implements Icon {
    icon_scan('\ue65b'),
    icon_ali_pay('\ue69a');

    private char character;

    EcIcons(char character) {
        this.character = character;
    }

    @Override
    public String key() {
        return name().replace('_', '-');
    }

    @Override
    public char character() {
        return character;
    }
}