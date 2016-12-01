package com.zzhoujay.richeditor.ext;

import android.support.annotation.IntDef;

/**
 * Created by zhou on 2016/11/30.
 * 储存StyleType、SpanState和三个byte长度参数的容器
 */
public class StyleTypeStateSpec {

    private static final int STATE_SHIFT = 30;
    private static final int TYPE_SHIFT = 24;
    private static final int ARGS_3_SHIFT = 16;
    private static final int ARGS_2_SHIFT = 8;

    private static final int MASK_STATE = 0x3;
    private static final int MASK_TYPE = 0x3f;
    private static final int MASK_ARG = 0xff;


    public static int makeStyleTypeStateSpec(int type, @StyleTypeState int state, byte... args) {
        int s = 0;
        if (args.length > 0) {
            s += args[0];
        }
        if (args.length > 1) {
            s += args[1] << ARGS_2_SHIFT;
        }
        if (args.length > 2) {
            s += args[2] << ARGS_3_SHIFT;
        }
        s += type << TYPE_SHIFT;
        s += state << STATE_SHIFT;
        return s;
    }

    public static int getStyleType(int typeSpec) {
        return (typeSpec >>> TYPE_SHIFT) & MASK_TYPE;
    }

    public static int getState(int typeSpec) {
        return (typeSpec >>> STATE_SHIFT) & MASK_STATE;
    }

    public static int getArgs1(int typeSpec) {
        return typeSpec & MASK_ARG;
    }

    public static int getArgs2(int typeSpec) {
        return (typeSpec >>> ARGS_2_SHIFT) & MASK_ARG;
    }

    public static int getArgs3(int typeSpec) {
        return (typeSpec >>> ARGS_3_SHIFT) & MASK_ARG;
    }

    @IntDef({StyleTypeState.STATE_NONE, StyleTypeState.STATE_EXIST, StyleTypeState.STATE_ACTIVE})
    public @interface StyleTypeState {
        int STATE_NONE = 0;
        int STATE_EXIST = 1;
        int STATE_ACTIVE = 2;
    }

}
