package com.example.mad_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Admin_lesson_delete extends AppCompatActivity {

    ArrayAdapter adminArrayAdapter;
    DataBaseHelper dataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_lesson_delete);

        final ListView lv_lessonDelete_j = findViewById(R.id.lv_lessonDelete_j);

        dataBaseHelper = new DataBaseHelper(Admin_lesson_delete.this);

        ShowLessonsOnListView(lv_lessonDelete_j);

        //to delete
        lv_lessonDelete_j.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                AdminModel clickedLesson = (AdminModel) adapterView.getItemAtPosition(i);
                dataBaseHelper.deleteOne(clickedLesson);
                ShowLessonsOnListView(lv_lessonDelete_j);
                Toast.makeText(Admin_lesson_delete.this, "Deleted "+ clickedLesson.toString(), Toast.LENGTH_SHORT).show();
            }

        });

    }

    private void ShowLessonsOnListView(ListView lv_lessonDetails_j) {
        adminArrayAdapter = new ArrayAdapter<AdminModel>(Admin_lesson_delete.this, android.R.layout.simple_list_item_1,dataBaseHelper.getData());
        lv_lessonDetails_j. setAdapter(adminArrayAdapter);
    }
    }
