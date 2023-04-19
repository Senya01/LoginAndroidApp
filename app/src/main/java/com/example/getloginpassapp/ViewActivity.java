package com.example.getloginpassapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        TextView tv = findViewById(R.id.tvViewFullName);
        Intent intent = getIntent();
        String full_name = intent.getStringExtra("full_name");
        tv.setText(String.format("Hello, %s", full_name));
    }
}