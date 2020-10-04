package com.example.mad_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class userShowAnswers extends AppCompatActivity {
    private ListView listView5;
    private ListView listview4 ;
    ArrayAdapter questionArrayAdapter;
    private questionDB dbhandler;
    private List<Question2> list6;
    private TextView textView6;
    private Button button7;
    ImageView backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_show_answers);

        dbhandler = new questionDB(this);
        listview4 = findViewById(R.id.displayAnswer3);
        textView6 = findViewById(R.id.countQuestion5);
        button7 = findViewById(R.id.addQuestion5);
        button7.setText("+");
        backBtn = findViewById(R.id.back);

        int count = dbhandler.countQuestion();

        list6 = new ArrayList<>();
        list6 = dbhandler.getAllAnswers();


        textView6.setText(count+" answers Available.");

        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),AddQuestion.class);
                startActivity(intent);
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        showAnswersOnListView(listview4);

    }
    public void showAnswersOnListView(ListView listView6){

        /*Question ques2 = new Question();*/
        questionArrayAdapter = new ArrayAdapter<Question2>(userShowAnswers.this,android.R.layout.simple_list_item_1,dbhandler.getAllAnswers());
        listView6.setAdapter(questionArrayAdapter);
    }
}