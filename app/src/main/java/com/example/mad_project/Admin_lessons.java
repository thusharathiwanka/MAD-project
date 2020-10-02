package com.example.mad_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class Admin_lessons extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_lessons);

        TextView Lessons = findViewById(R.id.Lessons);
        Lessons.setText(R.string.page2Name);

        Button button2_1 = findViewById(R.id.button2_1);
        button2_1.setText(R.string.btn2_1);

        Button button2_2 = findViewById(R.id.button2_2);
        button2_2.setText(R.string.btn2_2);

        Button button2_3 = findViewById(R.id.button2_3);
        button2_3.setText(R.string.btn2_3);

    }
    public void goJava(View v) {
        Intent i = new Intent(this, Admin_Lesson_java.class);
        startActivity(i);
    }

    public void goPython(View v) {
        Intent i = new Intent(this, Admin_Lesson_python.class);
        startActivity(i);
    }

    public void goPHP(View v) {
        Intent i = new Intent(this, Admin_lesson_PHP.class);
        startActivity(i);
    }

    public void back(View v) {
        Intent i = new Intent(this, Admin_profile.class);
        startActivity(i);
    }
}