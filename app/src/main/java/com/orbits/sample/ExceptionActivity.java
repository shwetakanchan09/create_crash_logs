package com.orbits.sample;

import android.os.Bundle;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class ExceptionActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ScrollView scrollView = new ScrollView(this);
        TextView textView = new TextView(this);
        textView.setPadding(50, 50, 50, 50);
        scrollView.addView(textView);
        setContentView(scrollView);
        textView.setText(getIntent().getExtras().getString("error"));
    }
}
