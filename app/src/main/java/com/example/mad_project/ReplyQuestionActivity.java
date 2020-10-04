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

public class ReplyQuestionActivity extends AppCompatActivity {
    private EditText editText7,editText8,editText9,editText10;
    private Button button6;
    private Context context;
    private questionDB dbhandler;
    ImageView backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reply_question);

        context = this;
        dbhandler = new questionDB(context);
        //Edit Text
        editText7 = findViewById(R.id.answerEmail);
        editText8 = findViewById(R.id.answerModule);
        editText9 = findViewById(R.id.answerQuestion);
        editText10 = findViewById(R.id.answer);
        backBtn = findViewById(R.id.back);

        //Button
        button6 = findViewById(R.id.uploadBtn);

        final String id = getIntent().getStringExtra("ID");
        Question question1 = dbhandler.getSingleQuestion(Integer.parseInt(id));

        editText7.setText(question1.getEmail());
        editText8.setText(question1.getModule());
        editText9.setText(question1.getQuestion());

        //System.out.println(id);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editText7.getText().toString().matches("") || editText8.getText().toString().matches("") || editText9.getText().toString().matches("") || editText10.getText().toString().matches("")){
                    Toast.makeText(ReplyQuestionActivity.this,"Please Fill all the Fields.",Toast.LENGTH_LONG).show();
                }else{
                    String setEmail2 = editText7.getText().toString();
                    String setModule2 = editText8.getText().toString();
                    String setQuestion2 = editText9.getText().toString();
                    String setAnswer = editText10.getText().toString();

                    //create model class object
                    Question question = new Question(Integer.parseInt(id),setEmail2,setModule2,setQuestion2,setAnswer);
                    int state = dbhandler.setAnswer(question);
                    startActivity(new Intent(context,ShowAnswerActivity.class));
                }
                /*String setEmail2 = editText7.getText().toString();
                String setModule2 = editText8.getText().toString();
                String setQuestion2 = editText9.getText().toString();
                String setAnswer = editText10.getText().toString();

                //create model class object
                Question question = new Question(Integer.parseInt(id),setEmail2,setModule2,setQuestion2,setAnswer);
                int state = dbhandler.setAnswer(question);
                startActivity(new Intent(context,ShowAnswerActivity.class));*/
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