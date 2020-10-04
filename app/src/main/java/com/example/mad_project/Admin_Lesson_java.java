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

import es.dmoral.toasty.Toasty;

public class Admin_Lesson_java extends AppCompatActivity {

    ArrayAdapter adminArrayAdapter;
    DataBaseHelper dataBaseHelper;
    private List<AdminModel> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__lesson_java);

        dataBaseHelper = new DataBaseHelper(Admin_Lesson_java.this);

        list = new ArrayList<>();
        list = dataBaseHelper.getData();

        final ListView lv_lessonDetails_j = findViewById(R.id.lv_lessonDetails_j);

        Button button10 = findViewById(R.id.button10);
        button10.setText("+");

        dataBaseHelper = new DataBaseHelper(Admin_Lesson_java.this);

        ShowLessonsOnListView(lv_lessonDetails_j);

        lv_lessonDetails_j.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                final AdminModel adminModel = list.get(i);
                AlertDialog.Builder builder = new AlertDialog.Builder(Admin_Lesson_java.this);
                builder.setTitle(adminModel.getLname());
                builder.setMessage(adminModel.getLcontent());

                builder.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(Admin_Lesson_java.this, Admin_Lesson_java.class);
                        startActivity(intent);
                    }
                });

                builder.setNegativeButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(Admin_Lesson_java.this, Admin_lesson_update.class);
                        intent.putExtra("id",String.valueOf(adminModel.getId()));
                        startActivity(intent);
                    }
                });

                builder.setNeutralButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dataBaseHelper.deleteOne(adminModel);
                        ShowLessonsOnListView(lv_lessonDetails_j);

                    }
                });
                builder.show();
            }

        });

    }

    private void ShowLessonsOnListView(ListView lv_lessonDetails_j) {
        adminArrayAdapter = new ArrayAdapter<AdminModel>(Admin_Lesson_java.this, android.R.layout.simple_list_item_1,dataBaseHelper.getData());
        lv_lessonDetails_j. setAdapter(adminArrayAdapter);
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