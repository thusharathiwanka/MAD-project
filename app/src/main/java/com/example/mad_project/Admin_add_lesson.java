package com.example.mad_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class Admin_add_lesson extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_lesson);

        TextView addLessons = findViewById(R.id.addLesson);
        addLessons.setText("Add Lessons");

        TextView titleName = findViewById(R.id.titleName);
        titleName.setText("Lesson Name: ");

        Button button11 = findViewById(R.id.button11);
        button11.setText("Submit");

        TextView content = findViewById(R.id.content);
        content.setText("Content: ");

        Button button12 = findViewById(R.id.button12);
        button12.setText("Add Materials");
    }
}