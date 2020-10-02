package com.example.mad_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Collections;

public class Student_open_lesson extends AppCompatActivity {

    ListView lv_tcontent;
    private DataBaseHelper dataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_open_lesson);

        dataBaseHelper = new DataBaseHelper(Student_open_lesson.this);

        TextView addLessons = findViewById(R.id.addLesson);
        addLessons.setText("Lessons");

        lv_tcontent = findViewById(R.id.tcontent);

        Button goQuestion = findViewById(R.id.goQuestion);
        goQuestion.setText("Ask Questions");

        String id = getIntent().getStringExtra("id");
        AdminModel adminModel = dataBaseHelper.getSingleAdminModel(Integer.parseInt(id));

        id = getIntent().getStringExtra("id");
        ArrayAdapter<AdminModel> adminArrayAdapter = new ArrayAdapter<AdminModel>(Student_open_lesson.this, android.R.layout.simple_list_item_1, Collections.singletonList(dataBaseHelper.getSingleAdminModel(Integer.parseInt(id))));
        lv_tcontent. setAdapter(adminArrayAdapter);

    }

    public void back(View v) {
        Intent i = new Intent(this, Student_View_lessons.class);
        startActivity(i);
    }

    public void goQuestion(View v){
        Intent i = new Intent(this,AddQuestion.class);
        startActivity(i);
    }
}