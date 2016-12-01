package com.zzhoujay.richeditor.span;

import android.graphics.Typeface;
import android.os.Parcel;
import android.text.style.StyleSpan;

import com.zzhoujay.richeditor.StyleType;

/**
 * Created by zhou on 2016/11/28.
 */

public class BoldSpan extends StyleSpan implements Styleable {
    public BoldSpan() {
        super(Typeface.BOLD);
    }

    public BoldSpan(Parcel src) {
        super(src);
    }

    @Override
    public int getStyleType() {
        return StyleType.BOLD;
    }

    public static final Creator<BoldSpan> CREATOR=new Creator<BoldSpan>() {
        @Override
        public BoldSpan createFromParcel(Parcel source) {
            return new BoldSpan(source);
        }

        @Override
        public BoldSpan[] newArray(int size) {
            return new BoldSpan[size];
        }
    };
}
