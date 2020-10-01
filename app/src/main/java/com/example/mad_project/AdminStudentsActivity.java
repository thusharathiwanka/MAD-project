package com.example.mad_project;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;

public class AdminStudentsActivity extends AppCompatActivity {
    ArrayList<String> studentsList;
    ArrayAdapter<String> adapter;
    ListView usersList;
    DBHelperProfile retrieveDB = new DBHelperProfile(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_students);

        studentsList = new ArrayList<>();
        viewStudents();
    }

    private void viewStudents() {
        Cursor cursor = retrieveDB.viewAllStudents();

        if(cursor.getCount() == 0) {
            Toasty.error(getApplicationContext(), "There are no registered users", Toasty.LENGTH_SHORT).show();
        } else {
            studentsList.add(cursor.getString(1));
        }

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, studentsList);
        usersList.setAdapter(adapter);
    }
}