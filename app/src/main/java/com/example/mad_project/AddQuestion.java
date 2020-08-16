package com.example.mad_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddQuestion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question);

        //EditText
        final EditText editText1 = findViewById(R.id.email);
        final EditText editText2 = findViewById(R.id.module);
        final EditText editText3 = findViewById(R.id.question);

        //Button
        Button post = findViewById(R.id.postBtn);

        //button click listener
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //get data from edit text
                String email = editText1.getText().toString();
                String module = editText2.getText().toString();
                String question = editText3.getText().toString();

                //activity intent
                Intent intent = new Intent(AddQuestion.this, DisplayQuestionActivity.class);
                intent.putExtra("EMAIL", email);
                intent.putExtra("MODULE", module);
                intent.putExtra("QUESTION", question);
                startActivity(intent);



            }
        });
    }
}