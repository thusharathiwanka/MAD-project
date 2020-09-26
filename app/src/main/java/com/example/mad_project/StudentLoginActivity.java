package com.example.mad_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import es.dmoral.toasty.Toasty;

public class StudentLoginActivity extends AppCompatActivity {
    ImageView backBtn;
    TextView adminLogin;
    Button loginBtn;
    EditText usernameInput, passwordInput;

    String username;
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login);

        backBtn = findViewById(R.id.back);
        adminLogin = findViewById(R.id.adminLogin);
        loginBtn = findViewById(R.id.loginBtn);
        usernameInput = findViewById(R.id.emailInput);
        passwordInput = findViewById(R.id.passwordInput);
        final DBHelperProfile loginDB = new DBHelperProfile(this);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        adminLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AdminLoginActivity.class);
                startActivity(intent);
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = usernameInput.getText().toString();
                password = passwordInput.getText().toString();

                if(username.length() <= 0 || password.length() <= 0) {
                    Toasty.error(getApplicationContext(), "Please fill all the fields", Toasty.LENGTH_SHORT).show();
                }else {
                    boolean loginUser = loginDB.studentLoginCheck(username, password);

                    if (loginUser) {
                        Intent intent = new Intent(getApplicationContext(), StudentProfileActivity.class);
                        intent.putExtra("USERNAME", username);
                        startActivity(intent);
                        Toasty.success(getApplicationContext(), "Welcome back " + username, Toasty.LENGTH_SHORT).show();
                    } else {
                        Toasty.error(getApplicationContext(), "Your username or password is invalid", Toasty.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}