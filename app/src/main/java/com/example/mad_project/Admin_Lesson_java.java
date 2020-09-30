package com.example.mad_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class Admin_Lesson_java extends AppCompatActivity {

    ArrayAdapter adminArrayAdapter;
    DataBaseHelper dataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__lesson_java);

        final ListView lv_lessonDetails_j = findViewById(R.id.lv_lessonDetails_j);

        Button button10 = findViewById(R.id.button10);
        button10.setText("+");

        dataBaseHelper = new DataBaseHelper(Admin_Lesson_java.this);

        ShowLessonsOnListView(lv_lessonDetails_j);

    }

    private void ShowLessonsOnListView(ListView lv_lessonDetails_j) {
        adminArrayAdapter = new ArrayAdapter<AdminModel>(Admin_Lesson_java.this, android.R.layout.simple_list_item_1,dataBaseHelper.getData());
        lv_lessonDetails_j. setAdapter(adminArrayAdapter);
    }

    public void goAddLesson(View v) {
        Intent i = new Intent(this, Admin_add_lesson.class);
        startActivity(i);
    }

    public void goDeleteLesson(View v) {
        Intent i = new Intent(this, Admin_lesson_delete.class);
        startActivity(i);
    }
}