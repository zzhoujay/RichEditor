package com.zzhoujay.richeditordemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.text.method.LinkMovementMethod;

public class ViewActivity extends AppCompatActivity {

    public static final String TEXT = "text";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

//        Intent intent = getIntent();
//        CharSequence charSequence = intent.getParcelableExtra(TEXT);

        AppCompatTextView textView = (AppCompatTextView) findViewById(R.id.text);
        textView.setText(MainActivity.text);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
    }
}
