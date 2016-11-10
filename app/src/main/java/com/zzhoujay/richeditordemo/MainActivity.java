package com.zzhoujay.richeditordemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.zzhoujay.richeditor.EditStatus;
import com.zzhoujay.richeditor.RichEditorView;

public class MainActivity extends AppCompatActivity {

    RichEditorView richEditorView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        richEditorView = (RichEditorView) findViewById(R.id.edit);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 0, 0, "H1");
        menu.add(0, 1, 1, "H2");
        menu.add(0, 2, 2, "H3");
        menu.add(0, 3, 3, "H4");
        menu.add(0, 4, 4, "H5");
        menu.add(0, 5, 5, "H6");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 0:
                richEditorView.setHead(EditStatus.Head.h1);
                break;
            case 1:
                richEditorView.setHead(EditStatus.Head.h2);
                break;
            case 2:
                richEditorView.setHead(EditStatus.Head.h3);
                break;
            case 3:
                richEditorView.setHead(EditStatus.Head.h4);
                break;
            case 4:
                richEditorView.setHead(EditStatus.Head.h5);
                break;
            case 5:
                richEditorView.setHead(EditStatus.Head.h6);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
