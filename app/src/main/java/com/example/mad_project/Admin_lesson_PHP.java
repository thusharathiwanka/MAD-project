package com.example.mad_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class Admin_lesson_PHP extends AppCompatActivity {

    ArrayAdapter adminArrayAdapter;
    DataBaseHelper dataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_lesson_php);

        final ListView lv_lessonDetails_php = findViewById(R.id.lv_lessonDetails_php);

        Button button10 = findViewById(R.id.button10);
        button10.setText("+");

        dataBaseHelper = new DataBaseHelper(Admin_lesson_PHP.this);

        adminArrayAdapter = new ArrayAdapter<AdminModel>(Admin_lesson_PHP.this, android.R.layout.simple_list_item_1,dataBaseHelper.getData());
        lv_lessonDetails_php. setAdapter(adminArrayAdapter);
    }

    public void goAddLesson(View v) {
        Intent i = new Intent(this, Admin_add_lesson.class);
        startActivity(i);
    }
}