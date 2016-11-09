package com.zzhoujay.richeditor;

import android.support.annotation.ColorInt;
import android.support.annotation.IntDef;

/**
 * Created by zhou on 2016/11/9.
 */
public interface RichEditorController {

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

    void setHead(int start, int end, @Head int type);

    void setHead(@Head int type);

    void setList(int start, int end, @List int type);

    void setList(@List int type);

    void setItalic(int start, int end, boolean italic);

    void setItalic(boolean italic);

    void setEmphasize(int start, int end, boolean emphasize);

    void setEmphasize(boolean emphasize);

    void setLink(int start, int end, String url);

    void setLink(String url);

    void insertLink(int position, String title, String url);

    void insertLink(String title, String url);

    void insertImage(int position, String title, String url);

    void insertImage(String title, String url);

    void setQuota(int start, int end, boolean quota);

    void setQuota(boolean quota);

    void setColor(int start, int end, @ColorInt int color);

    void setColor(@ColorInt int color);

}
