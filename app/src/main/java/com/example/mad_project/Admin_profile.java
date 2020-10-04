package com.example.mad_project;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import es.dmoral.toasty.Toasty;

public class Admin_profile extends AppCompatActivity {

    Button adminLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_profile);

        TextView profile = findViewById(R.id.profile);
        profile.setText(R.string.Page1Name);

        TextView welcome = findViewById(R.id.welcome);
        welcome.setText(R.string.msg1);

        Button button1 = findViewById(R.id.button1);
        button1.setText(R.string.btn1);

        Button button2 = findViewById(R.id.button2);
        button2.setText(R.string.btn2);

        Button button3 = findViewById(R.id.button3);
        button3.setText(R.string.btn3);

        adminLogout = findViewById(R.id.adminLogout);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AdminStudentsActivity.class);
                startActivity(intent);
            }
        });

        adminLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CreateAlertDialog();

            }
        });
    }

    public void CreateAlertDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to Log out ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(Admin_profile.this, AdminLoginActivity.class);
                startActivity(intent);
                Toasty.success(getApplicationContext(), "Logged out Successfully !", Toasty.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(Admin_profile.this, Admin_profile.class);
                startActivity(intent);
            }
        });
        builder.create();
        builder.show();
    }

    public void goLesson(View v) {
        Intent i = new Intent(this, Admin_Lesson_java.class);
        startActivity(i);
    }

    public void goFeedback(View v) {
        Intent i = new Intent(this, Admin_FeedBackMainActivity.class);
        startActivity(i);
    }

    public void goStudent(View v) {
        Intent i = new Intent(this, AdminStudentsActivity.class);
        startActivity(i);
    }

    public void goQuestion(View v) {
        Intent i = new Intent(this, AdminViewQuestion.class);
        startActivity(i);
    }
}

