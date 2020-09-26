package com.example.mad_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Admin_add_lesson extends AppCompatActivity {

    Button button11, button12;
    EditText et_lname, et_lcontent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_lesson);

        TextView addLessons = findViewById(R.id.addLesson);
        addLessons.setText("Add Lessons");

        TextView titleName = findViewById(R.id.titleName);
        titleName.setText("Lesson Name: ");

        et_lname = findViewById(R.id.lname);
        et_lcontent = findViewById(R.id.lcontent);

        Button button11 = findViewById(R.id.button11);
        button11.setText("Submit");

        TextView content = findViewById(R.id.content);
        content.setText("Content: ");

        Button button12 = findViewById(R.id.button12);
        button12.setText("Add Materials");

        button11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AdminModel adminModel;
                try{
                    adminModel = new AdminModel(-1, et_lname.getText().toString(), et_lcontent.getText().toString());
                    Toast.makeText(Admin_add_lesson.this, "Successfully Submitted!", Toast.LENGTH_SHORT).show();
                }catch(Exception e){
                    Toast.makeText(Admin_add_lesson.this, "Error Occured!", Toast.LENGTH_SHORT).show();
                    adminModel = new AdminModel(-1,"error","error");
                }

                DataBaseHelper dataBaseHelper = new DataBaseHelper(Admin_add_lesson.this);

                boolean success = dataBaseHelper.addOne(adminModel);
                Toast.makeText(Admin_add_lesson.this, "Success: "+success,Toast.LENGTH_SHORT).show();
            }
        });

    }
}