package com.example.mad_project;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ShowAnswerActivity extends AppCompatActivity {
    Question question;
    private ListView listview3 ;
    ArrayAdapter questionArrayAdapter;
    private questionDB dbhandler;
    private List<Question2> list3;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_answer);

        dbhandler = new questionDB(this);
        listview3 = findViewById(R.id.answerDisplay);
        textView = findViewById(R.id.countAnswer);

        int count = dbhandler.countQuestion();

        list3 = new ArrayList<>();
        list3 = dbhandler.getAllAnswers();


        textView.setText(count+" answers Available.");


        showAnswersOnListView(listview3);

        listview3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int possition, long l) {
                final Question2 question2 = list3.get(possition);

                AlertDialog.Builder builder = new AlertDialog.Builder(ShowAnswerActivity.this);
                builder.setTitle(question2.getQuestion());
                builder.setMessage(question2.getAnswer());
                builder.show();

            }
        });
    }

    public void showAnswersOnListView(ListView listView2){

        /*Question ques2 = new Question();*/
        questionArrayAdapter = new ArrayAdapter<Question2>(ShowAnswerActivity.this,android.R.layout.simple_list_item_1,dbhandler.getAllAnswers());
        listView2.setAdapter(questionArrayAdapter);
    }
}