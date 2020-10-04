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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddQuestion extends AppCompatActivity {
    ImageView backBtn;
    questionDB qdb;
    EditText editText1,editText2,editText3;
    Button btnPost;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question);

        //EditText
         editText1 = findViewById(R.id.email);
         editText2 = findViewById(R.id.module);
         editText3 = findViewById(R.id.question);
         btnPost = findViewById(R.id.postBtn);
        backBtn = findViewById(R.id.back);
         context = this;
         qdb = new questionDB(context);



        //button click listener
        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(editText1.getText().toString().matches("") || editText2.getText().toString().matches("") || editText3.getText().toString().matches("")){
                    Toast.makeText(AddQuestion.this,"Please Fill all the Fields.",Toast.LENGTH_LONG).show();
                } else{
                    String email = editText1.getText().toString();
                    String module = editText2.getText().toString();
                    String question = editText3.getText().toString();

                    boolean checkEmail = emailValidation(email);

                    if (checkEmail){
                        Question q1 = new Question(email,module,question);
                        qdb.add(q1);
                        Toast.makeText(AddQuestion.this,"Data Inserted",Toast.LENGTH_LONG).show();
                        startActivity(new Intent(getApplicationContext(),DisplayQuestionActivity.class));
                    }else{
                        Toast.makeText(AddQuestion.this, "Enter Valid Email", Toast.LENGTH_LONG).show();
                    }

                   /* Question q1 = new Question(email,module,question);
                    qdb.add(q1);
                    Toast.makeText(AddQuestion.this,"Data Inserted",Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(),DisplayQuestionActivity.class));*/
                }



               /* boolean isInserted = qdb.insertData(editText1.getText().toString(),
                        editText2.getText().toString(),
                        editText3.getText().toString());

                if(isInserted = true){
                    Toast.makeText(AddQuestion.this,"Data Inserted",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(AddQuestion.this,"Data Not Inserted",Toast.LENGTH_LONG).show();
                }*/


                /*get data from edit text
                String email = editText1.getText().toString();
                String module = editText2.getText().toString();
                String question = editText3.getText().toString();

                //activity intent
                Intent intent = new Intent(AddQuestion.this, DisplayQuestionActivity.class);
                intent.putExtra("EMAIL", email);
                intent.putExtra("MODULE", module);
                intent.putExtra("QUESTION", question);
                startActivity(intent);*/



            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public static boolean emailValidation(String emailQuestion){
        String emailQuestion1 = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
        Pattern emailQuestion2 = Pattern.compile(emailQuestion1, Pattern.CASE_INSENSITIVE);
        Matcher emailQuestionMatcher = emailQuestion2.matcher(emailQuestion);

        return emailQuestionMatcher.find();
    }
}