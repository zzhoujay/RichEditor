package com.zzhoujay.richeditor.span;

import android.os.Parcel;
import android.text.style.StrikethroughSpan;

import com.zzhoujay.richeditor.StyleType;

/**
 * Created by zhou on 2016/11/28.
 */

public class DeleteSpan extends StrikethroughSpan implements Styleable {

    @Override
    public int getStyleType() {
        return StyleType.DELETE;
    }

    public static final Creator<DeleteSpan> CREATOR = new Creator<DeleteSpan>() {
        @Override
        public DeleteSpan createFromParcel(Parcel source) {
            return new DeleteSpan();
        }

        @Override
        public DeleteSpan[] newArray(int size) {
            return new DeleteSpan[size];
        }
    };
}
