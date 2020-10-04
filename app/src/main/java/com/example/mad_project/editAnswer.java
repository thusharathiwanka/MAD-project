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

import javax.mail.search.IntegerComparisonTerm;

 public class editAnswer extends AppCompatActivity {

    private EditText editText11,editText12,editText13,editText14;
    private Button button7;
    private questionDB dbhandler;
    private Context context;
    ImageView backBtn;

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
        backBtn = findViewById(R.id.back);

        //Button
        button7 = findViewById(R.id.answerUpBtn);

        final String id = getIntent().getStringExtra("id");
        Question2 question6 = dbhandler.getSingleAnswer(Integer.parseInt(id));

        editText11.setText(question6.getEmail());
        editText12.setText(question6.getModule());
        editText13.setText(question6.getQuestion());
        editText14.setText(question6.getAnswer());

        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if( editText11.getText().toString().matches("") || editText12.getText().toString().matches("") || editText13.getText().toString().matches("") || editText14.getText().toString().matches("")){
                    Toast.makeText(editAnswer.this,"Please Fill all the Fields.",Toast.LENGTH_LONG).show();
                }else{
                    String email = editText11.getText().toString();
                    String module = editText12.getText().toString();
                    String questiion = editText13.getText().toString();
                    String answer = editText14.getText().toString();

                    Question2 question2 = new Question2(Integer.parseInt(id),email,module,questiion,answer);
                    int state = dbhandler.answerUpdate(question2);
                    startActivity(new Intent(editAnswer.this,ShowAnswerActivity.class));
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