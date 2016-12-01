package com.zzhoujay.richeditorfunctionbar;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.widget.AppCompatImageButton;
import android.util.AttributeSet;
import android.util.SparseArray;

/**
 * Created by zhou on 2016/11/29.
 * 可以添加状态的ImageButton
 */
public class StateListImageButton extends AppCompatImageButton {

    private SparseArray<Drawable> drawableSparseArray;
    private int state;
    private StateChangeCallback stateChangeCallback;


    public StateListImageButton(Context context) {
        super(context);
        init(context);
    }

    public StateListImageButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public StateListImageButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }


    private void init(Context context) {
        drawableSparseArray = new SparseArray<>();
    }

    private void setBackground() {
        Drawable drawable = drawableSparseArray.get(state);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            setBackground(drawable);
        } else {
            setBackgroundDrawable(drawable);
        }
    }

    public void addState(int state, Drawable drawable) {
        drawableSparseArray.put(state, drawable);
        setBackground();
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
        setBackground();
        if (stateChangeCallback != null) {
            stateChangeCallback.stateChange(state);
        }
    }

    public StateChangeCallback getStateChangeCallback() {
        return stateChangeCallback;
    }

    public void setStateChangeCallback(StateChangeCallback stateChangeCallback) {
        this.stateChangeCallback = stateChangeCallback;
    }
}
