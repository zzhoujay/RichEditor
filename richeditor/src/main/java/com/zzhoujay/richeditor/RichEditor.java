package com.zzhoujay.richeditor;

import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;

import com.zzhoujay.richeditor.callback.CursorProvider;
import com.zzhoujay.richeditor.callback.SpannedProvider;
import com.zzhoujay.richeditor.ext.StyleTypeStateSpec;
import com.zzhoujay.richeditor.span.Styleable;

/**
 * Created by zhou on 2016/11/25.
 * 富文本编辑工具类
 */
public class RichEditor implements CharSequence {

    private SpannedProvider spannedProvider;
    private CursorProvider cursorProvider;

    public RichEditor(SpannedProvider spannedProvider, CursorProvider cursorProvider) {
        this.spannedProvider = spannedProvider;
        this.cursorProvider = cursorProvider;
    }

    public int endStyle(int style) {
        return endStyle(Style.get(style));
    }

    public <T extends Styleable> int endStyle(Style<T> style) {
        return endStyle(cursorProvider.getCursorStart(), spannedProvider.getSSB(), style.getSpanClass());
    }

    public int startStyle(int style, Object... args) {
        return startStyle(Style.get(style), args);
    }

    public <T extends Styleable> int startStyle(Style<T> style, Object... args) {
        return startStyleWithRange(style, getStart(), getEnd(), args);
    }

    public <T extends Styleable> int startStyleWithRange(Style<T> style, int start, int end, Object... args) {
        SpannableStringBuilder spannableStringBuilder = getSSB();
        int flag = start == end ? Spanned.SPAN_INCLUSIVE_INCLUSIVE : Spanned.SPAN_EXCLUSIVE_INCLUSIVE;
        Styleable span = style.needArgument() ? style.createSpan(args) : style.createSpan();
        if (style instanceof StyleExt) {
            ((StyleExt) style).beforeStyle(spannableStringBuilder, start, end);
        }
        startStyle(start, end, spannedProvider.getSSB(), span, flag);
        if (style instanceof StyleExt) {
            ((StyleExt) style).afterStyle(spannableStringBuilder, start, end);
        }
        return StyleTypeStateSpec.StyleTypeState.STATE_ACTIVE;
    }

    public int[] getStyleTypeState() {
        return getStyleTypeState(getStart(), getEnd(), getSSB());
    }

    private <T extends Styleable> int endStyle(int offset, Spannable spannable, Class<T> clazz) {
        T[] spans = spannable.getSpans(offset, offset, clazz);
        if (spans != null && spans.length > 0) {
            T s = spans[0];
            int flags = spannable.getSpanFlags(s);
            if (flags != Spanned.SPAN_EXCLUSIVE_EXCLUSIVE) {
                terminateStyle(spannable, s);
                return StyleTypeStateSpec.StyleTypeState.STATE_EXIST;
            } else {
                spannable.removeSpan(s);
                return StyleTypeStateSpec.StyleTypeState.STATE_NONE;
            }
        }
        return StyleTypeStateSpec.StyleTypeState.STATE_NONE;
    }

    private <T> void terminateStyle(Spannable spannable, T s) {
        int start = spannable.getSpanStart(s);
        int end = spannable.getSpanEnd(s);
        spannable.removeSpan(s);
        spannable.setSpan(s, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    }

    private void startStyle(int start, int end, Spannable spannable, Object span, int flag) {
        spannable.setSpan(span, start, end, flag);
    }

    private int[] getStyleTypeState(int start, int end, Spannable spannable) {
        Styleable[] styleables = spannable.getSpans(start, end, Styleable.class);
        if (styleables != null && styleables.length > 0) {
            int[] types = new int[styleables.length];
            for (int i = 0; i < styleables.length; i++) {
                int type = styleables[i].getStyleType();
                Style style = Style.get(type);
                int t;
                int flag = spannable.getSpanFlags(styleables[i]);
                if (style instanceof StyleExt) {
                    //noinspection unchecked
                    t = ((StyleExt) style).getStyleTypeState(flag, styleables[i]);
                } else {
                    int state;
                    if (flag == Spanned.SPAN_EXCLUSIVE_EXCLUSIVE) {
                        state = StyleTypeStateSpec.StyleTypeState.STATE_EXIST;
                    } else {
                        state = StyleTypeStateSpec.StyleTypeState.STATE_ACTIVE;
                    }
                    t = StyleTypeStateSpec.makeStyleTypeStateSpec(type, state);
                }
                types[i] = t;
            }
            return types;
        } else {
            return new int[0];
        }
    }

    private Style[] getStyle(int start, int end, Spannable spannable) {
        Styleable[] styleables = spannable.getSpans(start, end, Styleable.class);
        if (styleables != null && styleables.length > 0) {
            Style[] styles = new Style[styleables.length];
            for (int i = 0; i < styleables.length; i++) {
                styles[i] = Style.get(styleables[i]);
            }
            return styles;
        } else {
            return new Style[0];
        }
    }

    private int getStart() {
        return cursorProvider == null ? 0 : cursorProvider.getCursorStart();
    }

    private int getEnd() {
        return cursorProvider == null ? 0 : cursorProvider.getCursorEnd();
    }

    private SpannableStringBuilder getSSB() {
        return spannedProvider.getSSB();
    }

    private int findLastParagraphEnd(CharSequence charSequence, int offset) {
        if (charSequence.length() == offset + 1) {
            return offset;
        }
        if (charSequence.length() <= offset) {
            return charSequence.length();
        }
        while (charSequence.charAt(offset) != '\n') {
            offset--;
            if (offset <= 0) {
                return 0;
            }
        }
        return offset + 1;
    }

    public void replace(CharSequence charSequence, int start, int end) {
        getSSB().replace(start, end, charSequence);
    }

    @Override
    public int length() {
        return getSSB().length();
    }

    @Override
    public char charAt(int index) {
        return getSSB().charAt(index);
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return getSSB().subSequence(start, end);
    }
}
