package com.zzhoujay.richeditor;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Created by zhou on 2016/11/5.
 */

public class RichEditorView extends EditText implements RichEditorController {

    private EditStatus status;

    public RichEditorView(Context context) {
        super(context);
    }

    public RichEditorView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RichEditorView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public RichEditorView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

    }

    private SpannableStringBuilder getSSB() {
        Editable editable = getText();
        return (SpannableStringBuilder) editable;
    }


    @Override
    public void setHead(int start, int end, @Head int type) {

    }


    @Override
    public void setList(int start, int end, @List int type) {

    }


    @Override
    public void setItalic(int start, int end, boolean italic) {

    }


    @Override
    public void setEmphasize(int start, int end, boolean emphasize) {

    }


    @Override
    public void setLink(int start, int end, String url) {

    }


    @Override
    public void insertImage(int position, String title, String url) {

    }


    @Override
    public void setQuota(int start, int end, boolean quota) {

    }


    @Override
    public void setColor(int start, int end, @ColorInt int color) {

    }

    @Override
    public void insertLink(int position, String title, String url) {

    }

    @Override
    public void insertLink(String title, String url) {
        insertLink(getSelectionStart(), title, url);
    }

    @Override
    public void insertImage(String title, String url) {
        insertImage(getSelectionStart(), title, url);
    }

    @Override
    public void setHead(@Head int type) {
        setHead(getSelectionStart(), getSelectionEnd(), type);
    }

    @Override
    public void setList(@List int type) {
        setList(getSelectionStart(), getSelectionEnd(), type);
    }

    @Override
    public void setItalic(boolean italic) {
        setItalic(getSelectionStart(), getSelectionEnd(), italic);
    }

    @Override
    public void setEmphasize(boolean emphasize) {
        setEmphasize(getSelectionStart(), getSelectionEnd(), emphasize);
    }

    @Override
    public void setLink(String url) {
        setLink(getSelectionStart(), getSelectionEnd(), url);
    }

    @Override
    public void setQuota(boolean quota) {
        setQuota(getSelectionStart(), getSelectionEnd(), quota);
    }

    @Override
    public void setColor(@ColorInt int color) {
        setColor(getSelectionStart(), getSelectionEnd(), color);
    }
}
