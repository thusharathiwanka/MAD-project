package com.example.mad_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import es.dmoral.toasty.Toasty;

public class StudentRegisterActivity extends AppCompatActivity {
    ImageView backBtn;
    Button nextBtn;
    EditText usernameInput, emailInput, passwordInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_register);

        backBtn = findViewById(R.id.back);
        nextBtn = findViewById(R.id.nextRegisterBtn);
        emailInput = findViewById(R.id.emailInput);
        usernameInput = findViewById(R.id.usernameInput);
        passwordInput = findViewById(R.id.passwordInput);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailInput.getText().toString();
                String username = usernameInput.getText().toString();
                String password = passwordInput.getText().toString();

                if(email.length() <= 0 || username.length() <= 0 || password.length() <= 0) {
                    Toasty.error(getApplicationContext(), "Please fill all the fields", Toasty.LENGTH_SHORT).show();
                }else {
                    Intent intent = new Intent(getApplicationContext(), SelectFavouritesActivity.class);
                    intent.putExtra("USER_EMAIL", email);
                    intent.putExtra("USER_NAME", username);
                    intent.putExtra("USER_PASSWORD", password);
                    startActivity(intent);
                }
            }
        });
    }
}