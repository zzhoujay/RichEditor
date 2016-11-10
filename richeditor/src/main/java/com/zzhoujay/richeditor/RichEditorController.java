package com.zzhoujay.richeditor;

import android.support.annotation.ColorInt;

/**
 * Created by zhou on 2016/11/9.
 */
public interface RichEditorController {

    void setHead(int start, int end, @EditStatus.Head int type);

    void setHead(@EditStatus.Head int type);

    void setList(int start, int end, @EditStatus.List int type);

    void setList(@EditStatus.List int type);

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

    boolean undo();

    boolean redo();

}
