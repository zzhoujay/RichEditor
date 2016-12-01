package com.zzhoujay.richeditor.span;

import android.os.Parcel;
import android.text.style.ForegroundColorSpan;

import com.zzhoujay.richeditor.StyleType;

/**
 * Created by zhou on 2016/12/1.
 */

public class ColorSpan extends ForegroundColorSpan implements Styleable {
    public ColorSpan(int color) {
        super(color);
    }

    private ColorSpan(Parcel src) {
        super(src);
    }

    @Override
    public int getStyleType() {
        return StyleType.COLOR;
    }

    public static final Creator<ColorSpan> CREATOR = new Creator<ColorSpan>() {
        @Override
        public ColorSpan createFromParcel(Parcel source) {
            return new ColorSpan(source);
        }

        @Override
        public ColorSpan[] newArray(int size) {
            return new ColorSpan[size];
        }
    };
}
