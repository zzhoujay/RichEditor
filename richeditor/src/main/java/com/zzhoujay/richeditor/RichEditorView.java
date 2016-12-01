package com.zzhoujay.richeditor;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.text.SpannableStringBuilder;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.EditText;

import com.zzhoujay.richeditor.callback.CursorChangeListener;
import com.zzhoujay.richeditor.callback.CursorProvider;
import com.zzhoujay.richeditor.callback.SpannedProvider;

/**
 * Created by zhou on 2016/11/5.
 * 支持富文本编辑的EditText
 */
public class RichEditorView extends EditText implements SpannedProvider, CursorProvider {

    private CursorChangeListener cursorChangeListener;
    private int lastPosition;

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

    private void init() {
    }


    @Override
    public int getCursorStart() {
        return getSelectionStart();
    }

    @Override
    public int getCursorEnd() {
        return getSelectionEnd();
    }

    @Override
    public SpannableStringBuilder getSSB() {
        return (SpannableStringBuilder) getText();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean r = super.onTouchEvent(event);
        callCursorChange();
        return r;
    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
        callCursorChange();
    }

    private void callCursorChange() {
        int p = getSelectionStart();
        if (p != lastPosition && cursorChangeListener != null) {
            cursorChangeListener.cursorChange(p);
        }
        lastPosition = p;
    }

    public void setCursorChangeListener(CursorChangeListener cursorChangeListener) {
        this.cursorChangeListener = cursorChangeListener;
    }
}
