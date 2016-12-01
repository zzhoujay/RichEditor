package com.zzhoujay.richeditor.span;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Parcel;
import android.support.annotation.IntDef;
import android.text.TextPaint;
import android.text.style.AbsoluteSizeSpan;

import com.zzhoujay.richeditor.StyleType;

/**
 * Created by zhou on 2016/11/26.
 */

public class HeadSpan extends AbsoluteSizeSpan implements Styleable {

    private static final int COLOR = Color.parseColor("#333333");


    @IntDef({HeadSize.H1, HeadSize.H2, HeadSize.H3})
    public @interface HeadSize {
        int H1 = 1;
        int H2 = 2;
        int H3 = 3;
    }

    private static final int H1 = 32;
    private static final int H2 = 28;
    private static final int H3 = 24;

    private static int getHeadSize(@HeadSize int index) {
        return index == HeadSize.H1 ? H1 : index == HeadSize.H2 ? H2 : H3;
    }

    @HeadSize
    private final int headSize;

    public HeadSpan(@HeadSize int size) {
        super(getHeadSize(size), true);
        this.headSize = size;
    }

    private HeadSpan(Parcel src) {
        super(src);
        int size = getSize();
        if (size >= H1) {
            headSize = HeadSize.H1;
        } else if (size >= H2) {
            headSize = HeadSize.H2;
        } else {
            headSize = HeadSize.H3;
        }
    }

    @Override
    public void updateDrawState(TextPaint ds) {
        super.updateDrawState(ds);
        applyStyle(ds);
        ds.setColor(COLOR);
    }

    @Override
    public void updateMeasureState(TextPaint ds) {
        super.updateMeasureState(ds);
        applyStyle(ds);
    }

    @HeadSize
    public int getHeadSize() {
        return headSize;
    }

    private static void applyStyle(Paint paint) {
        int style = Typeface.BOLD;
        int oldStyle;

        Typeface old = paint.getTypeface();
        if (old == null) {
            oldStyle = 0;
        } else {
            oldStyle = old.getStyle();
        }

        int want = oldStyle | style;

        Typeface tf;
        if (old == null) {
            tf = Typeface.defaultFromStyle(want);
        } else {
            tf = Typeface.create(old, want);
        }

        int fake = want & ~tf.getStyle();

        if ((fake & Typeface.BOLD) != 0) {
            paint.setFakeBoldText(true);
        }

        if ((fake & Typeface.ITALIC) != 0) {
            paint.setTextSkewX(-0.25f);
        }

        paint.setTypeface(tf);
    }

    public static final Creator<HeadSpan> CREATOR = new Creator<HeadSpan>() {
        @Override
        public HeadSpan createFromParcel(Parcel source) {
            return new HeadSpan(source);
        }

        @Override
        public HeadSpan[] newArray(int size) {
            return new HeadSpan[size];
        }
    };

    @Override
    public int getStyleType() {
        return StyleType.HEAD;
    }

}
