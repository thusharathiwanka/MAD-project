package com.example.mad_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import es.dmoral.toasty.Toasty;

public class ForgetPasswordActivity extends AppCompatActivity {
    EditText email;
    Button submitBtn;
    ImageView backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        email = findViewById(R.id.emailInputForget);
        submitBtn = findViewById(R.id.submitBtn);
        backBtn = findViewById(R.id.back);
        final DBHelperProfile dbHelper = new DBHelperProfile(this);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailInput = email.getText().toString();
                boolean isExists = dbHelper.checkEmailForget(emailInput);

                if(isExists) {
                    String characters = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ!@#$%^&*";
                    String newPassword = shuffleCharacters(characters);
                    boolean isChanged = dbHelper.updatePassword(emailInput, newPassword);

                    if(isChanged) {
                        sendMail(emailInput, newPassword);
                    }
                } else {
                    Toasty.error(getApplicationContext(), "Sorry your email does not match with any records", Toasty.LENGTH_SHORT).show();
                }
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), StudentLoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private String shuffleCharacters(String characters) {
        List<String> letters = Arrays.asList(characters.split(""));
        Collections.shuffle(letters);
        String shuffled = "";
        
        for (String letter : letters) {
            shuffled += letter;
        }
        return shuffled.substring(0, 10);
    }

    public void sendMail(String email, String newPassword) {
        String subject= "Reset Password";
        String message = "Your password has been changed. Your new password is " + newPassword + ". This is automatically generated password. You can change it after login";

        JavaMailAPI javaMailAPI = new JavaMailAPI(this, email, subject, message);
        javaMailAPI.execute();
    }
}