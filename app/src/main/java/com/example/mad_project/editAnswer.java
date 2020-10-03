 package com.example.mad_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

 public class editAnswer extends AppCompatActivity {

    private EditText editText11,editText12,editText13,editText14;
    private Button button7;
    private questionDB dbhandler;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_answer);

        context = this;
        dbhandler = new questionDB(context);

        //EditText
        editText11 = findViewById(R.id.answerUpEmail);
        editText12 = findViewById(R.id.answerUpModule);
        editText13 = findViewById(R.id.answerUpQuestion);
        editText14 = findViewById(R.id.answerUpdate);

        //Button
        button7 = findViewById(R.id.answerUpBtn);

        final String id = getIntent().getStringExtra("id");
        Question2 question6 = dbhandler.getSingleAnswer(Integer.parseInt(id));

        editText11.setText(question6.getEmail());
        editText12.setText(question6.getModule());
        editText13.setText(question6.getQuestion());
        editText14.setText(question6.getAnswer());


    }
}