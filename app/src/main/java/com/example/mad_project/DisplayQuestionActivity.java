package com.example.mad_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DisplayQuestionActivity extends AppCompatActivity {

    private Button button,button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_question);

        Intent intent = getIntent();
        String email = intent.getStringExtra("EMAIL");
        String module = intent.getStringExtra("MODULE");
        String question = intent.getStringExtra("QUESTION");

        TextView textView = findViewById(R.id.display);
        textView.setText(""+email+"\n"+module+"\n"+question);

        button2 = findViewById(R.id.showAnswer);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity3();
            }
        });

    }
    public void openActivity3(){
        Intent intent = new Intent(this, ShowAnswerActivity.class);
    }
}