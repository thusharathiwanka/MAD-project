package com.example.mad_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.AlertDialog;
import android.content.DialogInterface;
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

public class   StudentProfileActivity extends AppCompatActivity {
    Button editProfileBtn;
    ImageView menuBtn;
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    TextView usernameView, emailView, favouritesView;
    String userName;
    String email, username, password, favourite;

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
        final AlertDialog.Builder alert = new AlertDialog.Builder(this);

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
                    startActivity(new Intent(getApplicationContext(), Student_View_lessons.class));
                } else if (id == R.id.nav_ask_question) {
                    startActivity(new Intent(getApplicationContext(), DisplayQuestionActivity.class));
                } else if (id == R.id.nav_about) {
                    startActivity(new Intent(getApplicationContext(), AboutActivity.class));
                } else if (id == R.id.nav_feedback) {
                    startActivity(new Intent(getApplicationContext(), activity_feedback.class));
                } else if (id == R.id.nav_my_todos) {
                    startActivity(new Intent(getApplicationContext(), ToDoMainActivity.class));
                } else if(id == R.id.nav_contact) {
                    startActivity(new Intent(getApplicationContext(), activity_contact_via_gmail.class));
                } else if (id == R.id.nav_log_out) {
                    alert.setMessage("Are you sure you want to logout ?");
                    alert.setCancelable(false);
                    alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            startActivity(new Intent(getApplicationContext(), StudentLoginActivity.class));
                        }
                    });

                    alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            drawerLayout.closeDrawer(GravityCompat.START);
                        }
                    });
                    alert.create().show();
                } else if (id == R.id.nav_delete_profile) {
                    alert.setMessage("Are you sure you want to delete your profile ?");
                    alert.setCancelable(false);
                    alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            int isDeleted = retreiveDB.deleteStudent(userName);

                            if(isDeleted == 1) {
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent);

                                Toasty.success(getApplicationContext(), "Your account has been deleted successfully", Toasty.LENGTH_SHORT).show();
                            } else {
                                Toasty.error(getApplicationContext(), "Something went wrong when deleting your account", Toasty.LENGTH_SHORT).show();
                            }
                        }
                    });

                    alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            drawerLayout.closeDrawer(GravityCompat.START);
                        }
                    });
                    alert.create().show();
                }

                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });

        Intent intent = getIntent();
        userName = intent.getStringExtra("USERNAME");

        Cursor cursor = retreiveDB.getStudentInfo(userName);

        if(cursor.getCount() == 0) {
            Intent intent1 = getIntent();
            String username = intent1.getStringExtra("USERNAME");
            System.out.println(username);
            Cursor cursor1 = retreiveDB.getStudentInfo(userName);

            while (cursor1.moveToNext()) {
                email = cursor.getString(1);
                emailView.setText(email);
                username  = cursor.getString(2);
                usernameView.setText(username);
                password = cursor.getString(3);
                favourite = cursor.getString(4);
                favouritesView.setText(favourite);
            }
            return;
        } else {
            while (cursor.moveToNext()) {
                email = cursor.getString(1);
                emailView.setText(email);
                username  = cursor.getString(2);
                usernameView.setText(username);
                password = cursor.getString(3);
                favourite = cursor.getString(4);
                favouritesView.setText(favourite);
            }
        }

        editProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), EditStudentActivity.class);
                intent.putExtra("USERNAME", username);
                intent.putExtra("EMAIL", email);
                intent.putExtra("PASSWORD", password);
                intent.putExtra("FAVOURITES", favourite);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        Toasty.info(getApplicationContext(), "Select logout from drawer menu", Toasty.LENGTH_SHORT).show();
    }
}