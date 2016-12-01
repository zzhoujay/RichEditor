package com.zzhoujay.richeditor.span;

import android.os.Parcel;
import android.text.style.UnderlineSpan;

import com.zzhoujay.richeditor.StyleType;

/**
 * Created by zhou on 2016/11/28.
 */

public class UnderLineSpan extends UnderlineSpan implements Styleable {
    @Override
    public int getStyleType() {
        return StyleType.UNDER_LINE;
    }

    public static final Creator<UnderLineSpan> CREATOR = new Creator<UnderLineSpan>() {
        @Override
        public UnderLineSpan createFromParcel(Parcel source) {
            return new UnderLineSpan();
        }

        @Override
        public UnderLineSpan[] newArray(int size) {
            return new UnderLineSpan[size];
        }
    };
}
