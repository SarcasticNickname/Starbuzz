package com.example.starbuzz.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.starbuzz.R;

public class TopLevelActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_level);
    }
}