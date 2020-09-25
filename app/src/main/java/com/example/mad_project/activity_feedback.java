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


        mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openNewActivity();
            }
        });


    }
    public void openNewActivity(){
        Intent intent = new Intent(this, activity_contact_via_gmail.class);
        startActivity(intent);
    }
}