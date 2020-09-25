package com.example.mad_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class activity_feedback extends AppCompatActivity {

    Button btn1,btn2;
    FloatingActionButton mail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        btn1 = findViewById(R.id.add_feedback);
        btn2 = findViewById(R.id.back);
        mail = findViewById(R.id.gomail);
    }
}