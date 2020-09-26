package com.example.mad_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;

public class SelectFavouritesActivity extends AppCompatActivity {
    ImageView backBtn;
    Button registerBtn;
    ArrayList<String> btnValues = new ArrayList<>();

    String email;
    String username;
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_favourites);

        backBtn = findViewById(R.id.back);
        registerBtn = findViewById(R.id.finishBtn);
        final DBHelperProfile registerDB = new DBHelperProfile(this);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(btnValues.isEmpty()) {
                    Toasty.error(getApplicationContext(), "Select at least one favourite", Toasty.LENGTH_SHORT).show();
                } else {
                    boolean checkEmail = registerDB.checkUsername(email);
                    boolean checkUsername = registerDB.checkUsername(username);

                    if(checkEmail) {
                        Toasty.error(getApplicationContext(), "This email is already registered", Toasty.LENGTH_SHORT).show();
                    } else if(checkUsername) {
                        Toasty.error(getApplicationContext(), "This username is already taken", Toasty.LENGTH_SHORT).show();
                    } else {
                        boolean insertStudents = registerDB.insertStudents(email, username, password, btnValues);

                        if (insertStudents) {
                            Toasty.success(getApplicationContext(), "Your account has been created", Toasty.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), StudentLoginActivity.class);
                            startActivity(intent);
                        } else {
                            Toasty.success(getApplicationContext(), "Something went wrong with your registration", Toasty.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        email = intent.getStringExtra("STUDENT_EMAIL");
        username = intent.getStringExtra("STUDENT_USERNAME");
        password  = intent.getStringExtra("STUDENT_PASSWORD");
    }

    public void favouriteClicks(View view) {
        view.setBackgroundColor(Color.parseColor("#3583EF"));
        Button btn = (Button) view;
        btn.setTextColor(Color.parseColor("#FFFFFF"));

        int btnID = view.getId();
        String btnValue = null;

        switch (btnID) {
            case R.id.javaBtn:
                btnValue = "Java";
                break;
            case R.id.htmlBtn:
                btnValue = "HTML";
                break;
            case R.id.cBtn:
                btnValue = "C";
                break;
            case R.id.cssBtn:
                btnValue = "CSS";
                break;
            case R.id.cPlusPlusBtn:
                btnValue = "C++";
                break;
            case R.id.phpBtn:
                btnValue = "PHP";
                break;
            case R.id.jsBtn:
                btnValue = "JavaScript";
                break;
            case R.id.reactJsBtn:
                btnValue = "React JS";
                break;
            case R.id.pythonBtn:
                btnValue = "Python";
                break;
            case R.id.nodeJsBtn:
                btnValue = "Node JS";
                break;
            case R.id.sqlBtn:
                btnValue = "SQL";
                break;
            default:
                System.out.println("Something went wrong");
        }
        btnValues.add(btnValue);
    }
}