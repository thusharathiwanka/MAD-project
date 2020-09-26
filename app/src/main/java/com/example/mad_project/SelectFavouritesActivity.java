package com.example.mad_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class SelectFavouritesActivity extends AppCompatActivity {
    ImageView backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_favourites);

        backBtn = findViewById(R.id.back);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        String email = intent.getStringExtra("USER_EMAIL");
        String username = intent.getStringExtra("USER_NAME");
        String password  = intent.getStringExtra("USER_PASSWORD");
    }

    public void favouriteClicks(View view) {
        view.setBackgroundColor(Color.parseColor("#3583EF"));
        Button btn = (Button) view;
        btn.setTextColor(Color.parseColor("#FFFFFF"));
    }
}