package com.example.mad_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class DisplayQuestionActivity extends AppCompatActivity {

    private Button button,button2;

    ArrayAdapter questionArrayAdapter;
    private Button button4;
    private TextView textView2;
    private ListView listView2;
    private questionDB dbhandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_question);

        dbhandler = new questionDB(this);
        button4 = findViewById(R.id.addQuestion2);
        button4.setText("+");
        textView2 = findViewById(R.id.countQuestion2);
        listView2 = findViewById(R.id.questionID2);

        int count = dbhandler.countQuestion();
        textView2.setText("You have "+count+" questions.");

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),AddQuestion.class);
                startActivity(intent);
            }
        });

        showQuestionOnListView(listView2);

    }
    public void openActivity3(){

        Intent intent = new Intent(this, ShowAnswerActivity.class);
        startActivity(intent);
    }
    public void showQuestionOnListView(ListView listView2){
        questionArrayAdapter = new ArrayAdapter<Question>(DisplayQuestionActivity.this,android.R.layout.simple_list_item_1,dbhandler.getAllData());
        listView2.setAdapter(questionArrayAdapter);
    }
}