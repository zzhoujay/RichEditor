package com.zzhoujay.richeditorfunctionbar;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.AppCompatSpinner;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.HorizontalScrollView;

import com.zzhoujay.richeditor.RichEditor;
import com.zzhoujay.richeditor.Style;
import com.zzhoujay.richeditor.StyleType;
import com.zzhoujay.richeditor.callback.CursorChangeListener;
import com.zzhoujay.richeditor.callback.CursorProvider;
import com.zzhoujay.richeditor.ext.StyleTypeStateSpec;

import java.util.HashSet;

/**
 * Created by zhou on 2016/11/29.
 * 富文本编辑器功能栏
 */
public class RichEditorFunctionBar extends HorizontalScrollView implements CursorChangeListener {

    private RichEditor richEditor;
    private CursorProvider cursorProvider;
    private SparseArray<StateListImageButton> stateListImageButtonSparseArray;
    private HashSet<Integer> typeSet;
    private AppCompatSpinner head;

    public RichEditorFunctionBar(Context context) {
        super(context);
        init(context);
    }

    public RichEditorFunctionBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public RichEditorFunctionBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public RichEditorFunctionBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    public void setRichEditor(RichEditor richEditor) {
        this.richEditor = richEditor;
    }

    public void setCursorProvider(CursorProvider cursorProvider) {
        this.cursorProvider = cursorProvider;
    }

    private void init(Context context) {
        setScrollBarStyle(SCROLLBARS_INSIDE_INSET);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            setScrollBarSize(getScrollBarSize() / 2);
        }
        @SuppressLint("InflateParams") View view = LayoutInflater.from(context).inflate(R.layout.layout_function_bar, null);
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        addView(view, params);
        initView();
        initButton(context);
    }

    private void initView() {
        head = (AppCompatSpinner) findViewById(R.id.spinner);
        head.setAdapter(new SpinnerAdapter());
        head.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    richEditor.endStyle(StyleType.HEAD);
                } else {
                    richEditor.startStyle(Style.get(StyleType.HEAD), position);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        StateListImageButton bold = (StateListImageButton) findViewById(R.id.btn_bold);
        StateListImageButton italic = (StateListImageButton) findViewById(R.id.btn_italic);
        StateListImageButton code = (StateListImageButton) findViewById(R.id.btn_code);
        StateListImageButton underline = (StateListImageButton) findViewById(R.id.btn_underline);
        StateListImageButton delete = (StateListImageButton) findViewById(R.id.btn_delete);
        StateListImageButton quote = (StateListImageButton) findViewById(R.id.btn_quote);
        AppCompatImageButton link = (AppCompatImageButton) findViewById(R.id.btn_link);
        link.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String src = null;
                final int start = cursorProvider.getCursorStart();
                final int end = cursorProvider.getCursorEnd();
                if (start < end) {
                    src = richEditor.subSequence(start, end).toString();
                }
                createLinkInsertDialog(v.getContext(), new LinkInsertCallback() {
                    @Override
                    public void call(String text, String link) {
                        richEditor.replace(text, start, end);
                        richEditor.startStyleWithRange(Style.get(StyleType.LINK), start, start + text.length(), link);
                    }
                }, src).show();
            }
        });

        stateListImageButtonSparseArray = new SparseArray<>();
        typeSet = new HashSet<>();

        register(StyleType.BOLD, bold);
        register(StyleType.ITALIC, italic);
        register(StyleType.DELETE, delete);
        register(StyleType.UNDER_LINE, underline);
        register(StyleType.QUOTE, quote);
        register(StyleType.CODE, code);
    }

    private void register(int type, StateListImageButton button) {
        stateListImageButtonSparseArray.append(type, button);
        typeSet.add(type);
    }

    private void initButton(Context context) {
        Drawable normal = ContextCompat.getDrawable(context, R.drawable.bg_normal_shape);
        Drawable select = ContextCompat.getDrawable(context, R.drawable.bg_select_shape);
        Drawable highlight = ContextCompat.getDrawable(context, R.drawable.bg_highlight_shap);

        for (int type : typeSet) {
            final StateListImageButton stateListImageButton = stateListImageButtonSparseArray.get(type);
            stateListImageButton.addState(StyleTypeStateSpec.StyleTypeState.STATE_NONE, normal);
            stateListImageButton.addState(StyleTypeStateSpec.StyleTypeState.STATE_EXIST, select);
            stateListImageButton.addState(StyleTypeStateSpec.StyleTypeState.STATE_ACTIVE, highlight);
            stateListImageButton.setOnClickListener(new StateListImageButtonOnClickListener(type));
        }
    }

    @Override
    public void cursorChange(int newPosition) {
        int[] typeStates = richEditor.getStyleTypeState();
        resetButtonState(StyleTypeStateSpec.StyleTypeState.STATE_NONE);
        if (typeStates.length > 0) {
            for (int typeState : typeStates) {
                int type = StyleTypeStateSpec.getStyleType(typeState);
                int state = StyleTypeStateSpec.getState(typeState);
                if (type == StyleType.HEAD) {
                    int arg1 = StyleTypeStateSpec.getArgs1(typeState);
                    head.setSelection(arg1);
                } else {
                    StateListImageButton stateListImageButton = stateListImageButtonSparseArray.get(type);
                    if (stateListImageButton != null) {
                        stateListImageButton.setState(state);
                    }
                }
            }
        }
    }

    private void resetButtonState(int state) {
        for (int type : typeSet) {
            stateListImageButtonSparseArray.get(type).setState(state);
        }
        head.setSelection(0);
    }

    private class StateListImageButtonOnClickListener implements OnClickListener {

        private int type;

        StateListImageButtonOnClickListener(int type) {
            this.type = type;
        }

        @Override
        public void onClick(View v) {
            StateListImageButton stateListImageButton = ((StateListImageButton) v);
            int state = stateListImageButton.getState();
            if (state == StyleTypeStateSpec.StyleTypeState.STATE_NONE) {
                stateListImageButton.setState(richEditor.startStyle(type));
            } else if (state == StyleTypeStateSpec.StyleTypeState.STATE_ACTIVE) {
                stateListImageButton.setState(richEditor.endStyle(type));
            } else if (state == StyleTypeStateSpec.StyleTypeState.STATE_EXIST) {
                stateListImageButton.setState(richEditor.endStyle(type));
            }
        }
    }

    private interface LinkInsertCallback {
        void call(String text, String link);
    }

    private AlertDialog createLinkInsertDialog(Context context, final LinkInsertCallback callback, String src) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View view = LayoutInflater.from(context).inflate(R.layout.layout_link_insert, null);
        final EditText text = (EditText) view.findViewById(R.id.dialog_edit_1);
        final EditText link = (EditText) view.findViewById(R.id.dialog_edit_2);
        if (src != null) {
            text.setText(src);
        }
        builder.setTitle("Insert Link").setIcon(R.drawable.ic_link).setView(view)
                .setPositiveButton("insert", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String t = text.getText().toString();
                        String l = link.getText().toString();
                        if (callback != null) {
                            callback.call(t, l);
                        }
                    }
                }).setNegativeButton("cancel", null);
        return builder.create();
    }


}
