package com.uwimona.group25.calculuscal;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;



public class AboutActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        TextView textView = (TextView) findViewById(R.id.about);
        textView.setText(getString(R.string.text_about));

    }

}
