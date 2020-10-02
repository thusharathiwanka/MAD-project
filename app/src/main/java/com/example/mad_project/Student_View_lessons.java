package com.example.mad_project;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Student_View_lessons extends AppCompatActivity {

    ArrayAdapter adminArrayAdapter;
    DataBaseHelper dataBaseHelper;
    private List<AdminModel> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student__view_lessons);

        dataBaseHelper = new DataBaseHelper(Student_View_lessons.this);

        list = new ArrayList<>();
        list = dataBaseHelper.getData();

        final ListView lv_studentLesson = findViewById(R.id.lv_studentLesson);

        dataBaseHelper = new DataBaseHelper(Student_View_lessons.this);

        ShowLessonsOnListView(lv_studentLesson);

        lv_studentLesson.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                final AdminModel adminModel = list.get(i);
                AlertDialog.Builder builder = new AlertDialog.Builder(Student_View_lessons.this);
                builder.setTitle(adminModel.getLname());
                builder.setMessage(adminModel.getLcontent());

                builder.setPositiveButton("Got Any Questions?", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(Student_View_lessons.this, AddQuestion.class);
                        startActivity(intent);
                    }
                });

                builder.setNegativeButton("View Lesson", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(Student_View_lessons.this, Student_open_lesson.class);
                        intent.putExtra("id",String.valueOf(adminModel.getId()));
                        startActivity(intent);
                    }
                });

                builder.show();
            }

        });

    }

    private void ShowLessonsOnListView(ListView lv_studentLesson) {
        adminArrayAdapter = new ArrayAdapter<AdminModel>(Student_View_lessons.this, android.R.layout.simple_list_item_1,dataBaseHelper.getData());
        lv_studentLesson.setAdapter(adminArrayAdapter);
    }

    public void goQuestion(View v) {
        Intent i = new Intent(this, AddQuestion.class);
        startActivity(i);
    }

    public void back(View v) {
        Intent i = new Intent(this, Student_View_lessons.class);
        startActivity(i);
    }
}