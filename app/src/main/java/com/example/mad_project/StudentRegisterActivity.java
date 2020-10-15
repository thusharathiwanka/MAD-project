package com.example.mad_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        final DBHelperProfile registerDB = new DBHelperProfile(this);

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailInput.getText().toString();
                String username = usernameInput.getText().toString();
                String password = passwordInput.getText().toString();

                if(email.length() <= 0 || username.length() <= 0 || password.length() <= 0) {
                    Toasty.error(getApplicationContext(), "Please fill all the fields", Toasty.LENGTH_SHORT).show();
                }else {
                    boolean isValidEmail  = emailValidate(email);

                    if(isValidEmail) {
                        boolean isValidUsername = usernameValidate(username);

                        if(isValidUsername) {
                            boolean checkEmail = registerDB.checkEmail(email);
                            boolean checkUsername = registerDB.checkUsername(username);

                            if(checkEmail) {
                                Toasty.error(getApplicationContext(), "This email is already registered", Toasty.LENGTH_SHORT).show();
                            } else if(checkUsername) {
                                Toasty.error(getApplicationContext(), "This username is already taken", Toasty.LENGTH_SHORT).show();
                            } else {
                                Intent intent = new Intent(getApplicationContext(), SelectFavouritesActivity.class);
                                intent.putExtra("STUDENT_EMAIL", email);
                                intent.putExtra("STUDENT_USERNAME", username);
                                intent.putExtra("STUDENT_PASSWORD", password);
                                startActivity(intent);
                            }
                        } else {
                            Toasty.error(getApplicationContext(), "Enter a valid username. Username should contain characters and numbers", Toasty.LENGTH_SHORT).show();
                        }
                    } else {
                        Toasty.error(getApplicationContext(), "Enter a valid email", Toasty.LENGTH_SHORT).show();
                    }
                }
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public static boolean emailValidate(String email) {
        String emailRegex = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
        Pattern emailPattern = Pattern.compile(emailRegex, Pattern.CASE_INSENSITIVE);
        Matcher emailMatcher = emailPattern.matcher(email);

        return emailMatcher.find();
    }

    public static boolean usernameValidate(String username) {
        String usernameRegex = "^[aA-zZ0-9_-]\\w{5,30}$";
        Pattern usernamePattern = Pattern.compile(usernameRegex);
        Matcher usernameMatcher = usernamePattern.matcher(username);

        return usernameMatcher.matches();
    }
}