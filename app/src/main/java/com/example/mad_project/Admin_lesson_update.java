package com.example.mad_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Admin_lesson_update extends AppCompatActivity {

    EditText et_lname, et_lcontent;
    private DataBaseHelper dataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_lesson_update);

        dataBaseHelper = new DataBaseHelper(Admin_lesson_update.this);

        TextView addLessons = findViewById(R.id.addLesson);
        addLessons.setText("Update Lessons");

        TextView titleName = findViewById(R.id.titleName);
        titleName.setText("Lesson Name: ");

        et_lname = findViewById(R.id.lname);
        et_lcontent = findViewById(R.id.lcontent);

        Button Update = findViewById(R.id.update);
        Update.setText("Update and Submit");

        TextView content = findViewById(R.id.content);
        content.setText("Content: ");

        Button button12 = findViewById(R.id.button12);
        button12.setText("Add Materials");

        final String id = getIntent().getStringExtra("id");
        AdminModel adminModel = dataBaseHelper.getSingleAdminModel(Integer.parseInt(id));

        et_lname.setText(adminModel.getLname());
        et_lcontent.setText(adminModel.getLcontent());

        Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String l_name = et_lname.getText().toString();
                String l_content = et_lcontent.getText().toString();

                AdminModel adminModel = new AdminModel(Integer.parseInt(id), l_name, l_content);
                int state = dataBaseHelper.update(adminModel);
                Toast.makeText(Admin_lesson_update.this, "Successfully Updated!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Admin_lesson_update.this,Admin_Lesson_java.class));

               /* try{
                   /* adminModel = new AdminModel(-1, et_lname.getText().toString(), et_lcontent.getText().toString());
                    Toast.makeText(Admin_lesson_update.this, "Successfully Updated!", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(Admin_lesson_update.this,Admin_Lesson_java.class);
                    startActivity(i);

                }catch(Exception e){
                    Toast.makeText(Admin_lesson_update.this, "Error Occured!", Toast.LENGTH_SHORT).show();
                    adminModel = new AdminModel(-1,"error","error");
                }

                DataBaseHelper dataBaseHelper = new DataBaseHelper(Admin_lesson_update.this);

                boolean success = dataBaseHelper.addOne(adminModel);*/
                //Toast.makeText(Admin_add_lesson.this, "Success: "+success,Toast.LENGTH_SHORT).show();
            }
        });

    }
}