package com.zzhoujay.richeditor.span;

import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Parcel;
import android.support.annotation.ColorInt;
import android.text.ParcelableSpan;
import android.text.TextPaint;
import android.text.style.MetricAffectingSpan;

/**
 * Created by zhou on 2016/11/10.
 */

public class HeadSpan extends MetricAffectingSpan implements ParcelableSpan {

    private final float size;
    private final int style;
    @ColorInt
    private final int color;

    public HeadSpan(float size, int style,@ColorInt int color) {
        this.size = size;
        this.style = style;
        this.color=color;
    }

    public HeadSpan(Parcel parcel) {
        this(parcel.readFloat(), parcel.readInt(),parcel.readInt());
    }

    @Override
    public int getSpanTypeId() {
        return 0;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeFloat(size);
        dest.writeInt(style);
        dest.writeInt(color);
    }

    @Override
    public void updateMeasureState(TextPaint p) {
        apply(p,style);
        p.setTextSize(p.getTextSize() * size);
    }

    @Override
    public void updateDrawState(TextPaint tp) {
        updateMeasureState(tp);
        tp.setColor(color);
    }
    private static void apply(Paint paint, int style) {
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
}
