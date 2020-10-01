package com.example.mad_project;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;

public class AdminStudentsActivity extends AppCompatActivity {
    ArrayList<String> studentsNameList;
    ArrayAdapter<String> adapterNames;
    ListView usersList;
    ImageView backBtn;
    DBHelperProfile retrieveDB = new DBHelperProfile(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_students);

        usersList = findViewById(R.id.usersList);
        backBtn = findViewById(R.id.back);

        studentsNameList = new ArrayList<>();
        viewStudents();

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        usersList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String user = usersList.getItemAtPosition(i).toString();
                Cursor cursor = retrieveDB.getStudentInfo(user);
                String userId = null;
                String userEmail = null;
                String userName = null;
                String userFavourites = null;

                while (cursor.moveToNext()) {
                    userId = cursor.getString(0);
                    userEmail = cursor.getString(1);
                    userName = cursor.getString(2);
                    userFavourites = cursor.getString(4);
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(AdminStudentsActivity.this);
                builder.setTitle("Student Information");
                builder.setMessage("Student ID: " + userId + "\n" + "Student Email: " + userEmail + "\n" + "Student Username: " + userName + "\n" + "Student's Favourites: " + userFavourites);
                builder.setPositiveButton("OK", null);
                builder.show();
            }
        });
    }

    private void viewStudents() {
        Cursor cursor = retrieveDB.viewAllStudents();

        if(cursor.getCount() == 0) {
            Toasty.error(getApplicationContext(), "There are no registered users", Toasty.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                studentsNameList.add(cursor.getString(2));
            }
        }

        adapterNames = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, studentsNameList);
        usersList.setAdapter(adapterNames);
    }
}