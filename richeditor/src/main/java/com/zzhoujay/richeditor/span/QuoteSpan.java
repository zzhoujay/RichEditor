package com.zzhoujay.richeditor.span;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Parcel;
import android.text.Layout;

import com.zzhoujay.richeditor.StyleType;

/**
 * Created by zhou on 2016/11/28.
 */

public class QuoteSpan extends android.text.style.QuoteSpan implements Styleable {

    private static final int QUOTE_COLOR = Color.parseColor("#DDDDDD");
    private static final int STRIPE_WIDTH = 15;
    private static final int GAP_WIDTH = 40;

    @Override
    public int getStyleType() {
        return StyleType.QUOTE;
    }

    public QuoteSpan() {
        super(QUOTE_COLOR);
    }

    private QuoteSpan(Parcel src) {
        super(src);
    }

    @Override
    public int getLeadingMargin(boolean first) {
        return STRIPE_WIDTH + GAP_WIDTH;
    }

    @Override
    public void drawLeadingMargin(Canvas c, Paint p, int x, int dir, int top, int baseline, int bottom, CharSequence text, int start, int end, boolean first, Layout layout) {
        Paint.Style style = p.getStyle();
        int color = p.getColor();

        p.setStyle(Paint.Style.FILL);
        p.setColor(getColor());

        c.drawRect(x, top, x + dir * STRIPE_WIDTH, bottom, p);

        p.setStyle(style);
        p.setColor(color);
    }

    public static final Creator<QuoteSpan> CREATOR = new Creator<QuoteSpan>() {
        @Override
        public QuoteSpan createFromParcel(Parcel source) {
            return new QuoteSpan(source);
        }

        @Override
        public QuoteSpan[] newArray(int size) {
            return new QuoteSpan[size];
        }
    };
}
