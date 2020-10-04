package com.example.mad_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class EditQuestion extends AppCompatActivity {

    EditText editText4,editText5,editText6;
    Button button5;
    private questionDB dbhandler;
    private Context context;
    ImageView backBtn;


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
        backBtn = findViewById(R.id.back);


        final String id2 = getIntent().getStringExtra("ID");
        Question question = dbhandler.getSingleQuestion(Integer.parseInt(id2));

        editText4.setText(question.getEmail());
        editText5.setText(question.getModule());
        editText6.setText(question.getQuestion());
        //System.out.println(id)
        // Edit button
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editText4.getText().toString().matches("") || editText5.getText().toString().matches("") || editText6.getText().toString().matches("")){
                    Toast.makeText(EditQuestion.this,"Please Fill all the Fields.",Toast.LENGTH_LONG).show();
                }else{
                    String setEmail = editText4.getText().toString();
                    String setModule = editText5.getText().toString();
                    String setQuestion = editText6.getText().toString();

                    Question question = new Question(Integer.parseInt(id2),setEmail,setModule,setQuestion);
                    int state = dbhandler.updateQuestion(question);
                    startActivity(new Intent(context,DisplayQuestionActivity.class));
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
}