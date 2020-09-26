package com.example.mad_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class StudentRegisterActivity extends AppCompatActivity {
    ImageView backBtn;
    Button nextBtn;
    EditText username, email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_register);

        backBtn = findViewById(R.id.back);
        nextBtn = findViewById(R.id.nextRegisterBtn);
        email = findViewById(R.id.emailInput);
        username = findViewById(R.id.usernameInput);
        password = findViewById(R.id.passwordInput);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SelectFavouritesActivity.class);
                intent.putExtra("USER_EMAIL", email.getText().toString());
                intent.putExtra("USER_NAME", username.getText().toString());
                intent.putExtra("USER_PASSWORD", password.getText().toString());
                startActivity(intent);
            }
        });
    }
}