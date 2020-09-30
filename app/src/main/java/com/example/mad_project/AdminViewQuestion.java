package com.example.mad_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AdminViewQuestion extends AppCompatActivity {

    private Button button,button3;
    private TextView textView1;
    private questionDB dbhandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_question);

        dbhandler = new questionDB(this);
        button3 = findViewById(R.id.addQuestion);
        button3.setText("+");
        textView1 = findViewById(R.id.countQuestion);
        button = findViewById(R.id.replyBtn);

        //count
        int count = dbhandler.countQuestion();
        textView1.setText("You have "+count+" questions.");


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity2();
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),AddQuestion.class);
                startActivity(intent);
            }
        });

    }
    public void openActivity2(){
        Intent intent = new Intent(this, ReplyQuestionActivity.class);
        startActivity(intent);
    }
}