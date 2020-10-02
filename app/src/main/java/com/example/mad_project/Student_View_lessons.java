package com.example.mad_project;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

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

                builder.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(Student_View_lessons.this, Admin_Lesson_java.class);
                        startActivity(intent);
                    }
                });

                builder.setNegativeButton("View Lesson", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(Student_View_lessons.this, Admin_lesson_update.class);
                        intent.putExtra("id",String.valueOf(adminModel.getId()));
                        startActivity(intent);
                    }
                });

                builder.setNeutralButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dataBaseHelper.deleteOne(adminModel);
                        ShowLessonsOnListView(lv_studentLesson);
                        Toast.makeText(Student_View_lessons.this, "Deleted Successfully!", Toast.LENGTH_SHORT).show();
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

    public void goAddLesson(View v) {
        Intent i = new Intent(this, Admin_add_lesson.class);
        startActivity(i);
    }

    public void back(View v) {
        Intent i = new Intent(this, Admin_profile.class);
        startActivity(i);
    }
}