package com.zzhoujay.richeditor.span;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.text.style.ReplacementSpan;

import com.zzhoujay.richeditor.StyleType;

/**
 * Created by zhou on 2016/11/28.
 */

public class CodeSpan extends ReplacementSpan implements Styleable {

    private static final int CODE_COLOR = Color.parseColor("#F0F0F0");

    private static final float RADIUS = 10;

    private Drawable drawable;
    private float padding;
    private int width;

    public CodeSpan() {
        GradientDrawable d = new GradientDrawable();
        d.setColor(CODE_COLOR);
        d.setCornerRadius(RADIUS);
        drawable = d;
    }

    @Override
    public int getSize(Paint paint, CharSequence text, int start, int end, Paint.FontMetricsInt fm) {
        padding = paint.measureText("t");
        width = (int) (paint.measureText(text, start, end) + padding * 4);
        return width;
    }

    @Override
    public void draw(Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom, Paint paint) {
        drawable.setBounds((int) (x + padding), top, (int) (x - padding) + width, bottom);
        drawable.draw(canvas);
        canvas.drawText(text, start, end, x + padding * 2, y, paint);
    }

    @Override
    public int getStyleType() {
        return StyleType.CODE;
    }
}
