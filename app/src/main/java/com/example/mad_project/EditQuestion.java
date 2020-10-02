package com.example.mad_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditQuestion extends AppCompatActivity {

    EditText editText4,editText5,editText6;
    Button button5;
    private questionDB dbhandler;
    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_question);

        context = this;
        dbhandler = new questionDB(context);

        editText4 = findViewById(R.id.email2);
        editText5 = findViewById(R.id.module2);
        editText6 = findViewById(R.id.question2);
        button5 = findViewById(R.id.updateBtn);


        final String id2 = getIntent().getStringExtra("ID");
        Question question = dbhandler.getSingleQuestion(Integer.parseInt(id2));

        editText4.setText(question.getEmail());
        editText5.setText(question.getModule());
        editText6.setText(question.getQuestion());
        //System.out.println(id);



    }
}