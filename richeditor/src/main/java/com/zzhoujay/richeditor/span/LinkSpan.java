package com.zzhoujay.richeditor.span;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Parcel;
import android.provider.Browser;
import android.text.TextPaint;
import android.text.style.URLSpan;
import android.util.Log;
import android.view.View;

import com.zzhoujay.richeditor.StyleType;

/**
 * Created by zhou on 16-7-2.
 * 链接Span
 */
public class LinkSpan extends URLSpan implements Styleable {

    private static final int LINK_COLOR = Color.parseColor("#4078C0");


    public LinkSpan(String url) {
        super(url);
    }

    private LinkSpan(Parcel src) {
        super(src);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeInt(LINK_COLOR);
    }

    @Override
    public void updateDrawState(TextPaint ds) {
        super.updateDrawState(ds);
        ds.setColor(LINK_COLOR);
        ds.setUnderlineText(false);
    }

    @Override
    public void onClick(View widget) {
        String url = getURL();
        if (url.startsWith("www")) {
            url = "http://" + url;
        }
        Uri uri = Uri.parse(url);
        Context context = widget.getContext();
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        intent.putExtra(Browser.EXTRA_APPLICATION_ID, context.getPackageName());
        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Log.w("LinkSpan", "Activity was not found for intent, " + intent.toString());
        }
    }

    public static final Creator<LinkSpan> CREATOR = new Creator<LinkSpan>() {
        @Override
        public LinkSpan createFromParcel(Parcel source) {
            return new LinkSpan(source);
        }

        @Override
        public LinkSpan[] newArray(int size) {
            return new LinkSpan[size];
        }
    };

    @Override
    public int getStyleType() {
        return StyleType.LINK;
    }
}
