package com.zzhoujay.richeditor;

import android.support.annotation.IntDef;

/**
 * Created by zhou on 2016/11/28.
 * Style的种类
 */
@IntDef({StyleType.BOLD, StyleType.ITALIC, StyleType.DELETE, StyleType.UNDER_LINE, StyleType.QUOTE,
        StyleType.LINK, StyleType.IMAGE, StyleType.CODE, StyleType.HEAD, StyleType.COLOR})
public @interface StyleType {

    int BOLD = 1;
    int ITALIC = 2;
    int DELETE = 3;
    int UNDER_LINE = 4;
    int QUOTE = 5;
    int LINK = 6;
    int IMAGE = 7;
    int CODE = 8;
    int HEAD = 9;
    int COLOR = 10;

}
