package com.example.mad_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import es.dmoral.toasty.Toasty;

public class EditStudentActivity extends AppCompatActivity {
    ImageView backBtn;
    EditText usernameEdit, passwordEdit, emailEdit, favouriteEdit;
    Button saveBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_student);

        usernameEdit = findViewById(R.id.usernameEditInput);
        emailEdit = findViewById(R.id.emailEditInput);
        passwordEdit = findViewById(R.id.passwordEditInput);
        favouriteEdit = findViewById(R.id.favoritesEditInput);
        backBtn = findViewById(R.id.back);
        saveBtn = findViewById(R.id.saveProfileBtn);
        final DBHelperProfile updateDB = new DBHelperProfile(this);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Intent intent = getIntent();
        final String username = intent.getStringExtra("USERNAME");

        usernameEdit.setText(username);
        emailEdit.setText(intent.getStringExtra("EMAIL"));
        passwordEdit.setText(intent.getStringExtra("PASSWORD"));
        favouriteEdit.setText(intent.getStringExtra("FAVOURITES"));

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usernameUpdate = usernameEdit.getText().toString();
                String emailUpdate = emailEdit.getText().toString();
                String passwordUpdate = passwordEdit.getText().toString();
                String favouritesUpdate = favouriteEdit.getText().toString();

                boolean isUpdated =  updateDB.updateStudent(username, emailUpdate, usernameUpdate, passwordUpdate, favouritesUpdate);
                System.out.println(isUpdated);

                if(isUpdated) {
                    Toasty.success(getApplicationContext(), "Your details has been updated successfully", Toasty.LENGTH_SHORT).show();
                    Intent intent1 = new Intent(getApplicationContext(), StudentProfileActivity.class);
                    startActivity(intent1);
                } else {
                    Toasty.error(getApplicationContext(), "Something went wrong when updating your details", Toasty.LENGTH_SHORT).show();
                }
            }
        });
    }
}