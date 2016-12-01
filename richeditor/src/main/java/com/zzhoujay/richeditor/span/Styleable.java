package com.zzhoujay.richeditor.span;

import com.zzhoujay.richeditor.StyleType;

/**
 * Created by zhou on 2016/11/28.
 * 标记span为对应的style
 */
public interface Styleable {

    /**
     * 获取span对应的styleType
     *
     * @return StyleType
     */
    @StyleType
    int getStyleType();

}
