package com.zzhoujay.richeditor.span;

import android.graphics.Typeface;
import android.os.Parcel;
import android.text.style.StyleSpan;

import com.zzhoujay.richeditor.StyleType;

/**
 * Created by zhou on 2016/11/28.
 */

public class ItalicSpan extends StyleSpan implements Styleable {
    public ItalicSpan() {
        super(Typeface.ITALIC);
    }

    private ItalicSpan(Parcel in) {
        super(in);
    }

    @Override
    public int getStyleType() {
        return StyleType.ITALIC;
    }

    public static final Creator<ItalicSpan> CREATOR = new Creator<ItalicSpan>() {
        @Override
        public ItalicSpan createFromParcel(Parcel source) {
            return new ItalicSpan(source);
        }

        @Override
        public ItalicSpan[] newArray(int size) {
            return new ItalicSpan[size];
        }
    };
}
