package com.example.mad_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class activity_feedback extends AppCompatActivity {

    Button btn1,btn2;
    FloatingActionButton mail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        btn1 = findViewById(R.id.add_feedback);
        btn2 = findViewById(R.id.back);
        mail = findViewById(R.id.gomail);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openContactFeedbackActivity();
            }
        });


        mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openContactMailActivity();
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openBackActivity();
            }
        });

    }

    private void openContactFeedbackActivity() {
        Intent intent = new Intent(this, FeedBackMainActivity.class);
        startActivity(intent);
    }

    public void openContactMailActivity(){
        Intent intent = new Intent(this, activity_contact_via_gmail.class);
        startActivity(intent);
    }
    public void openBackActivity(){
        Intent intent = new Intent(this, Student_View_lessons.class);
        startActivity(intent);
    }
}