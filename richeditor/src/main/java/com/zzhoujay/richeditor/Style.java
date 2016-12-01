package com.zzhoujay.richeditor;

import android.graphics.drawable.Drawable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.util.SparseArray;

import com.zzhoujay.richeditor.ext.StyleTypeStateSpec;
import com.zzhoujay.richeditor.span.BoldSpan;
import com.zzhoujay.richeditor.span.CodeSpan;
import com.zzhoujay.richeditor.span.ColorSpan;
import com.zzhoujay.richeditor.span.DeleteSpan;
import com.zzhoujay.richeditor.span.HeadSpan;
import com.zzhoujay.richeditor.span.ImageSpan;
import com.zzhoujay.richeditor.span.ItalicSpan;
import com.zzhoujay.richeditor.span.LinkSpan;
import com.zzhoujay.richeditor.span.QuoteSpan;
import com.zzhoujay.richeditor.span.Styleable;
import com.zzhoujay.richeditor.span.UnderLineSpan;

/**
 * Created by zhou on 2016/11/26.
 * Style
 */
public abstract class Style<T extends Styleable> {

    private static final Style<BoldSpan> BOLD = new Style<BoldSpan>() {
        @Override
        public Class<BoldSpan> getSpanClass() {
            return BoldSpan.class;
        }

        @Override
        public BoldSpan createSpan() {
            return new BoldSpan();
        }

        @Override
        public int getStyleType() {
            return StyleType.BOLD;
        }
    };

    private static final Style<ItalicSpan> ITALIC = new Style<ItalicSpan>() {
        @Override
        public Class<ItalicSpan> getSpanClass() {
            return ItalicSpan.class;
        }

        @Override
        public ItalicSpan createSpan() {
            return new ItalicSpan();
        }

        @Override
        public int getStyleType() {
            return StyleType.ITALIC;
        }
    };

    private static final Style<DeleteSpan> DELETE = new Style<DeleteSpan>() {
        @Override
        public Class<DeleteSpan> getSpanClass() {
            return DeleteSpan.class;
        }

        @Override
        public DeleteSpan createSpan() {
            return new DeleteSpan();
        }

        @Override
        public int getStyleType() {
            return StyleType.DELETE;
        }
    };

    private static final Style<UnderLineSpan> UNDER_LINE = new Style<UnderLineSpan>() {
        @Override
        public Class<UnderLineSpan> getSpanClass() {
            return UnderLineSpan.class;
        }

        @Override
        public UnderLineSpan createSpan() {
            return new UnderLineSpan();
        }

        @Override
        public int getStyleType() {
            return StyleType.UNDER_LINE;
        }
    };

    private static final Style<QuoteSpan> QUOTE = new Style<QuoteSpan>() {
        @Override
        public Class<QuoteSpan> getSpanClass() {
            return QuoteSpan.class;
        }

        @Override
        public QuoteSpan createSpan() {
            return new QuoteSpan();
        }

        @Override
        public int getStyleType() {
            return StyleType.QUOTE;
        }

        @Override
        public boolean paragraph() {
            return true;
        }
    };

    private static final Style<LinkSpan> LINK = new Style<LinkSpan>() {
        @Override
        public Class<LinkSpan> getSpanClass() {
            return LinkSpan.class;
        }

        @Override
        public LinkSpan createSpan() {
            return null;
        }

        @Override
        public int getStyleType() {
            return StyleType.LINK;
        }

        @Override
        public LinkSpan createSpan(Object... args) {
            return new LinkSpan(args[0].toString());
        }

        @Override
        public boolean needArgument() {
            return true;
        }
    };

    private static final Style<ImageSpan> IMAGE = new Style<ImageSpan>() {
        @Override
        public Class<ImageSpan> getSpanClass() {
            return ImageSpan.class;
        }

        @Override
        public ImageSpan createSpan() {
            return null;
        }

        @Override
        public int getStyleType() {
            return StyleType.IMAGE;
        }

        @Override
        public ImageSpan createSpan(Object... args) {
            return new ImageSpan(((Drawable) args[1]), args[0].toString());
        }

        @Override
        public boolean needArgument() {
            return true;
        }
    };

    private static final Style<CodeSpan> CODE = new Style<CodeSpan>() {
        @Override
        public Class<CodeSpan> getSpanClass() {
            return CodeSpan.class;
        }

        @Override
        public CodeSpan createSpan() {
            return new CodeSpan();
        }

        @Override
        public int getStyleType() {
            return StyleType.CODE;
        }
    };

    private static final Style<HeadSpan> HEAD = new StyleExt<HeadSpan>() {
        @Override
        public Class<HeadSpan> getSpanClass() {
            return HeadSpan.class;
        }

        @Override
        public HeadSpan createSpan() {
            return null;
        }

        @Override
        public int getStyleType() {
            return StyleType.HEAD;
        }

        @Override
        public HeadSpan createSpan(Object... args) {
            return new HeadSpan(((int) args[0]));
        }

        @Override
        public boolean needArgument() {
            return true;
        }

        @Override
        public void beforeStyle(SpannableStringBuilder ssb, int start, int end) {
            HeadSpan[] headSpans = ssb.getSpans(start, end, HeadSpan.class);
            for (HeadSpan headSpan : headSpans) {
                int s = ssb.getSpanStart(headSpan);
                int e = ssb.getSpanEnd(headSpan);
                ssb.removeSpan(headSpan);
                ssb.setSpan(headSpan, s, e, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }

        @Override
        public int getStyleTypeState(int flag, HeadSpan headSpan) {
            int state;
            if (flag == Spanned.SPAN_EXCLUSIVE_EXCLUSIVE) {
                state = StyleTypeStateSpec.StyleTypeState.STATE_EXIST;
            } else {
                state = StyleTypeStateSpec.StyleTypeState.STATE_ACTIVE;
            }
            byte arg1 = (byte) headSpan.getHeadSize();
            return StyleTypeStateSpec.makeStyleTypeStateSpec(headSpan.getStyleType(), state, arg1);
        }
    };

    private static final Style<ColorSpan> COLOR = new Style<ColorSpan>() {
        @Override
        public Class<ColorSpan> getSpanClass() {
            return ColorSpan.class;
        }

        @Override
        public ColorSpan createSpan() {
            return null;
        }

        @Override
        public int getStyleType() {
            return StyleType.COLOR;
        }

        @Override
        public boolean needArgument() {
            return true;
        }

        @Override
        public ColorSpan createSpan(Object... args) {
            return new ColorSpan((Integer) args[0]);
        }
    };

    public abstract Class<T> getSpanClass();

    public abstract T createSpan();

    @StyleType
    public abstract int getStyleType();

    public T createSpan(Object... args) {
        return createSpan();
    }

    public boolean needArgument() {
        return false;
    }

    public boolean paragraph() {
        return false;
    }

    private static final SparseArray<Style> styles;

    private static void register(Style style) {
        styles.append(style.getStyleType(), style);
    }

    static {
        styles = new SparseArray<>();
        register(BOLD);
        register(ITALIC);
        register(DELETE);
        register(UNDER_LINE);
        register(QUOTE);
        register(LINK);
        register(IMAGE);
        register(CODE);
        register(HEAD);
        register(COLOR);
    }

    public static Style get(Styleable styleable) {
        return styles.get(styleable.getStyleType());
    }

    public static Style get(@StyleType int styleType) {
        return styles.get(styleType);
    }


}

/**
 * Style的扩展
 *
 * @param <T> style对应的span类型
 */
abstract class StyleExt<T extends Styleable> extends Style<T> {

    public void beforeStyle(SpannableStringBuilder ssb, int start, int end) {

    }

    public void afterStyle(SpannableStringBuilder ssb, int start, int end) {

    }

    public int getStyleTypeState(int flag, T t) {
        int state;
        if (flag == Spanned.SPAN_EXCLUSIVE_EXCLUSIVE) {
            state = StyleTypeStateSpec.StyleTypeState.STATE_EXIST;
        } else {
            state = StyleTypeStateSpec.StyleTypeState.STATE_ACTIVE;
        }
        return StyleTypeStateSpec.makeStyleTypeStateSpec(t.getStyleType(), state);
    }
}