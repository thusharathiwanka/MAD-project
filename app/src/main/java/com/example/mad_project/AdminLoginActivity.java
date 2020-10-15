package com.example.mad_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import es.dmoral.toasty.Toasty;

public class AdminLoginActivity extends AppCompatActivity {
    ImageView backBtn;
    EditText usernameInput, passwordInput;
    Button loginBtn;

    String username;
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        backBtn = findViewById(R.id.back);
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

        //Inserting admins
//        DBHelperProfile adminDB = new DBHelperProfile(this);
//        adminDB.insertAdmins("Thushara Thiwanka", "thushara@gmail.com", "thushara01", "thushara01", "Node JS, JavaScript");
//        adminDB.insertAdmins("Udeepa Desarana", "udeepa@gmail.com", "udeepa01", "udeepa01", "React JS, JavaScript");

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = usernameInput.getText().toString();
                password = passwordInput.getText().toString();

                if(username.length() <= 0 || password.length() <= 0) {
                    Toasty.error(getApplicationContext(), "Please fill all the fields", Toasty.LENGTH_SHORT).show();
                }else {
                    boolean isValidUsername = StudentRegisterActivity.usernameValidate(username);

                    if(isValidUsername) {
                        boolean loginAdmin = loginDB.adminLoginCheck(username, password);

                        if (loginAdmin) {
                            Intent intent = new Intent(getApplicationContext(), Admin_profile.class);
                            intent.putExtra("USERNAME", username);
                            startActivity(intent);
                            Toasty.success(getApplicationContext(), "Welcome back " + username, Toasty.LENGTH_SHORT).show();
                        } else {
                            Toasty.error(getApplicationContext(), "Your username or password is invalid", Toasty.LENGTH_SHORT).show();
                        }
                    } else {
                        Toasty.error(getApplicationContext(), "Enter a valid username", Toasty.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}