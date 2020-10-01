package com.example.mad_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class AdminViewQuestion extends AppCompatActivity {

    ArrayAdapter questionArrayAdapter;
    private Button button,button3;
    private TextView textView1;
    private ListView listView1;
    private questionDB dbhandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_question);

        dbhandler = new questionDB(this);
        button3 = findViewById(R.id.addQuestion);
        button3.setText("+");
        textView1 = findViewById(R.id.countQuestion);
        listView1 = findViewById(R.id.questionID);
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

        showQuestionOnListView(listView1);

    }
    public void openActivity2(){
        Intent intent = new Intent(this, ReplyQuestionActivity.class);
        startActivity(intent);
    }

    public void showQuestionOnListView(ListView listView2){
        questionArrayAdapter = new ArrayAdapter<Question>(AdminViewQuestion.this,android.R.layout.simple_list_item_1,dbhandler.getAllData());
        listView2.setAdapter(questionArrayAdapter);
    }

}