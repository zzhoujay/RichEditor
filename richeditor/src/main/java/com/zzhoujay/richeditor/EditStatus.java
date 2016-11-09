package com.zzhoujay.richeditor;

import android.graphics.Color;
import android.support.annotation.IntDef;

/**
 * Created by zhou on 2016/11/9.
 */

public class EditStatus {

    @IntDef({Head.unspecified, Head.h1, Head.h2, Head.h3, Head.h4, Head.h5, Head.h6})
    @interface Head {
        int unspecified = 0;
        int h1 = 1;
        int h2 = 2;
        int h3 = 3;
        int h4 = 4;
        int h5 = 5;
        int h6 = 6;
    }

    @IntDef({List.unspecified, List.unordered, List.order})
    @interface List {
        int unspecified = 0;
        int unordered = 1;
        int order = 2;
    }

    public int head;
    public int list;
    public boolean italic;
    public boolean emphasize;
    public int color;

    public EditStatus() {
        head = Head.unspecified;
        list = List.unspecified;
        italic = false;
        emphasize = false;
        color = Color.BLACK;
    }
}
