package com.example.mad_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import es.dmoral.toasty.Toasty;

public class StudentProfileActivity extends AppCompatActivity {
    Button editProfileBtn;
    ImageView menuBtn;
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    TextView usernameView, emailView, favouritesView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_drawer);

        editProfileBtn = findViewById(R.id.editBtn);
        menuBtn = findViewById(R.id.menuBtn);
        navigationView = findViewById(R.id.nav_view_student);
        drawerLayout = findViewById(R.id.drawer_layout);
        usernameView = findViewById(R.id.username);
        emailView = findViewById(R.id.email);
        favouritesView = findViewById(R.id.favorites);
        final DBHelperProfile retreiveDB = new DBHelperProfile(this);

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

                if(id == R.id.nav_learning) {
//            startActivity(new Intent(getApplicationContext(), ));
                } else if (id == R.id.nav_ask_question) {
                    startActivity(new Intent(getApplicationContext(), AddQuestion.class));
                } else if (id == R.id.nav_about) {
                    startActivity(new Intent(getApplicationContext(), AboutActivity.class));
                } else if (id == R.id.nav_feedback) {
//            startActivity(new Intent(getApplicationContext(),));
                } else if (id == R.id.nav_log_out) {
                    startActivity(new Intent(getApplicationContext(), StudentLoginActivity.class));
                }

                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });

        Intent intent = getIntent();
        String username = intent.getStringExtra("USERNAME");

        Cursor cursor = retreiveDB.getStudentInfo(username);

        if(cursor.getCount() == 0) {
            Toasty.error(getApplicationContext(), "Sorry, something went wrong with your info", Toasty.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                emailView.setText(cursor.getString(1));
                usernameView.setText(cursor.getString(2));
                favouritesView.setText(cursor.getString(4));
            }
        }
    }

    @Override
    public void onBackPressed() {
        Toasty.info(getApplicationContext(), "Select logout from drawer menu", Toasty.LENGTH_SHORT).show();
    }
}