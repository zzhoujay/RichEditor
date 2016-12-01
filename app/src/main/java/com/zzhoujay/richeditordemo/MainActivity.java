package com.zzhoujay.richeditordemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.zzhoujay.richeditor.RichEditor;
import com.zzhoujay.richeditor.RichEditorView;
import com.zzhoujay.richeditor.Style;
import com.zzhoujay.richeditor.StyleType;
import com.zzhoujay.richeditorfunctionbar.RichEditorFunctionBar;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    static CharSequence text;

    private static final String TAG = "MainActivity";
    private RichEditor richEditor;
    private Spinner spinner;

    private static final int[] types = {StyleType.BOLD, StyleType.ITALIC, StyleType.DELETE, StyleType.UNDER_LINE, StyleType.QUOTE};
    private RichEditorView richEditorView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        richEditorView = (RichEditorView) findViewById(R.id.edit);
        RichEditorFunctionBar functionBar = (RichEditorFunctionBar) findViewById(R.id.function_bar);

        richEditorView.setCursorChangeListener(functionBar);

        spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Arrays.asList("Bold", "Italic", "Delete", "UnderLine", "Quote")));

        findViewById(R.id.start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start();
            }
        });
        findViewById(R.id.end).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                end();
            }
        });

        richEditor = new RichEditor(richEditorView, richEditorView);
        functionBar.setRichEditor(richEditor);
        functionBar.setCursorProvider(richEditorView);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 0, 0, "View");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == 0) {
            Intent intent = new Intent(this, ViewActivity.class);
//            intent.putExtra(ViewActivity.TEXT,  richEditorView.getSSB());
            text = richEditorView.getSSB();
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    public void end() {
        int id = spinner.getSelectedItemPosition();
        richEditor.endStyle(Style.get(types[id]));
    }

    public void start() {
        int id = spinner.getSelectedItemPosition();
        richEditor.startStyle(Style.get(types[id]));
    }
}
