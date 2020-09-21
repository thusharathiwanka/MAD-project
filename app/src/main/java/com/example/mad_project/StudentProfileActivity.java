package com.example.mad_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class StudentProfileActivity extends AppCompatActivity {
    Button editProfileBtn;
    ImageView menuBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_activity_student_profile);

        editProfileBtn = findViewById(R.id.editBtn);
        menuBtn = findViewById(R.id.menuBtn);

        editProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), EditStudentActivity.class);
                startActivity(intent);
            }
        });

        menuBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                DrawerLayout navDrawer = findViewById(R.id.drawer_layout);
                // If the navigation drawer is not open then open it, if its already open then close it.
                if(!navDrawer.isDrawerOpen(GravityCompat.START)) {
                    navDrawer.openDrawer(Gravity.LEFT);
                }
                else {
                    navDrawer.closeDrawer(Gravity.RIGHT);
                }
            }
        });
    }
}