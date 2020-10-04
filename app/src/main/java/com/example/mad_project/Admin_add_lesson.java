package com.example.mad_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import es.dmoral.toasty.Toasty;

public class Admin_add_lesson extends AppCompatActivity {

    EditText et_lname, et_lcontent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_lesson);

        TextView addLessons = findViewById(R.id.addLesson);
        addLessons.setText("Add Lessons");

        final TextView titleName = findViewById(R.id.titleName);
        titleName.setText("Lesson Name: ");

        et_lname = findViewById(R.id.lname);
        et_lcontent = findViewById(R.id.lcontent);

        Button button11 = findViewById(R.id.button11);
        button11.setText("Submit");

        final TextView content = findViewById(R.id.content);
        content.setText("Content: ");

        button11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AdminModel adminModel;

                    try {
                        adminModel = new AdminModel(-1, et_lname.getText().toString(), et_lcontent.getText().toString());
                        Toasty.success(getApplicationContext(), "Successfully Submitted", Toasty.LENGTH_SHORT).show();
                        Intent i = new Intent(Admin_add_lesson.this, Admin_Lesson_java.class);
                        startActivity(i);

                    } catch (Exception e) {
                        Toast.makeText(Admin_add_lesson.this, "Error Occured!", Toast.LENGTH_SHORT).show();
                        adminModel = new AdminModel(-1, "error", "error");
                    }

                    DataBaseHelper dataBaseHelper = new DataBaseHelper(Admin_add_lesson.this);

                    boolean success = dataBaseHelper.addOne(adminModel);
                    //Toast.makeText(Admin_add_lesson.this, "Success: "+success,Toast.LENGTH_SHORT).show();

                }
        });

    }

    public void back(View v) {
        Intent i = new Intent(this, Admin_Lesson_java.class);
        startActivity(i);
    }
}