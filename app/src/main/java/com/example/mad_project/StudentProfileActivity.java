package com.example.mad_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.material.navigation.NavigationView;

public class StudentProfileActivity extends AppCompatActivity {
    Button editProfileBtn;
    ImageView menuBtn;
    NavigationView navigationView;
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_drawer);

        editProfileBtn = findViewById(R.id.editBtn);
        menuBtn = findViewById(R.id.menuBtn);
        navigationView = findViewById(R.id.nav_view_student);
        drawerLayout = findViewById(R.id.drawer_layout);

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

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                if(id == R.id.nav_profile) {
                    startActivity(new Intent(getApplicationContext(), StudentProfileActivity.class));
                } else if(id == R.id.nav_learning) {
//            startActivity(new Intent(getApplicationContext(), ));
                } else if (id == R.id.nav_ask_question) {
                    startActivity(new Intent(getApplicationContext(), AddQuestion.class));
                } else if (id == R.id.nav_about) {
                    startActivity(new Intent(getApplicationContext(), AboutActivity.class));
                } else if (id == R.id.nav_feedback) {
//            startActivity(new Intent(getApplicationContext(),));
                } else if (id == R.id.nav_log_out) {
//                    startActivity(new Intent(getApplicationContext(), AddQuestion.class));
                }

                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }
}