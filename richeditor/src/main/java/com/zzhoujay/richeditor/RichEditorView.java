package com.zzhoujay.richeditor;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.EditText;

import com.zzhoujay.richeditor.span.HeadSpan;

/**
 * Created by zhou on 2016/11/5.
 */

public class RichEditorView extends EditText implements RichEditorController {

    private static final int h_under_line_color = Color.parseColor("#eeeeee");
    private static final int h1_color = Color.parseColor("#333333");
    private static final int h6_color = Color.parseColor("#777777");

    private static final float scale_h1 = 2.25f;
    private static final float scale_h2 = 1.75f;
    private static final float scale_h3 = 1.5f;
    private static final float scale_h4 = 1.25f;
    private static final float scale_h5 = 1, scale_h6 = 1;

    private static final float[] scale_h = {scale_h1, scale_h2, scale_h3, scale_h4, scale_h5, scale_h6};


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
    public void setHead(int start, int end, @EditStatus.Head int type) {
        h(start, end, scale_h[type - 1], h1_color);
    }


    @Override
    public void setList(int start, int end, @EditStatus.List int type) {

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
    public void setHead(@EditStatus.Head int type) {
        setHead(getSelectionStart(), getSelectionEnd(), type);
    }

    @Override
    public void setList(@EditStatus.List int type) {
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

    @Override
    public boolean undo() {
        return false;
    }

    @Override
    public boolean redo() {
        return false;
    }

    private SpannableStringBuilder h(int start, int end, float s, int color) {
        Log.i("RichEditor", "start:" + start + ",end:" + end);
        SpannableStringBuilder spannableStringBuilder = getSSB();
        HeadSpan headSpan=new HeadSpan(s,Typeface.BOLD,color);
        spannableStringBuilder.setSpan(headSpan,start,end,Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
//        StyleSpan styleSpan = new StyleSpan(Typeface.BOLD);
//        RelativeSizeSpan sizeSpan = new RelativeSizeSpan(s);
//        ForegroundColorSpan colorSpan = new ForegroundColorSpan(color);
//        spannableStringBuilder.setSpan(styleSpan, start, end, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
//        spannableStringBuilder.setSpan(sizeSpan, start, end, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
//        spannableStringBuilder.setSpan(colorSpan, start, end, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        return spannableStringBuilder;
    }

//    private SpannableStringBuilder hWithUnderLine(int start, int end, float s) {
//        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(charSequence);
//        int start = 0;
//        StyleSpan styleSpan = new StyleSpan(Typeface.BOLD);
//        RelativeSizeSpan sizeSpan = new RelativeSizeSpan(s);
//        ForegroundColorSpan colorSpan = new ForegroundColorSpan(h1_color);
//        spannableStringBuilder.setSpan(styleSpan, 0, spannableStringBuilder.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//        spannableStringBuilder.setSpan(sizeSpan, 0, spannableStringBuilder.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//        spannableStringBuilder.setSpan(colorSpan, 0, spannableStringBuilder.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//        Drawable underLine = new ColorDrawable(h_under_line_color);
//        UnderLineSpan underLineSpan = new UnderLineSpan(underLine, getTextViewRealWidth(), 5);
//        spannableStringBuilder.append('\n');
//        start += charSequence.length() + 1;
//        spannableStringBuilder.append("$");
//        spannableStringBuilder.setSpan(underLineSpan, start, spannableStringBuilder.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//        return spannableStringBuilder;
//    }

    private int getTextViewRealWidth() {
        return getWidth() - getPaddingLeft() - getPaddingRight();
    }
}
