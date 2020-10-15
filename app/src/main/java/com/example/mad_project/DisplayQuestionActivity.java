package com.example.mad_project;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DisplayQuestionActivity extends AppCompatActivity {

    private Button button,button2;

    ArrayAdapter questionArrayAdapter;
    private Button button4,replyBtn;
    private TextView textView2;
    private ListView listView2;
    private questionDB dbhandler;
    private List<Question> list2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_question);

        dbhandler = new questionDB(this);
        button4 = findViewById(R.id.addQuestion2);
        button4.setText("+");
        replyBtn = findViewById(R.id.replyBtn);
        textView2 = findViewById(R.id.countQuestion2);
        listView2 = findViewById(R.id.questionID2);

        int count = dbhandler.countQuestion();

        list2 = new ArrayList<>();
        list2 = dbhandler.getAllData();


        textView2.setText("You have "+count+" questions.");

        replyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),userShowAnswers.class);
                startActivity(intent);
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),AddQuestion.class);
                startActivity(intent);
            }
        });

        showQuestionOnListView(listView2);

        //DialogBox
        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int possition2, long l) {

                final Question question5 = list2.get(possition2);

                AlertDialog.Builder builder = new AlertDialog.Builder(DisplayQuestionActivity.this);
                builder.setTitle(question5.getModule());
                builder.setMessage(question5.getQuestion());
                //builder.show();

                //Cancel
                builder.setPositiveButton("Cancel",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(DisplayQuestionActivity.this,DisplayQuestionActivity.class);
                        startActivity(intent);
                    }
                });

                //Delete
                builder.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dbhandler.deleteQuestions(question5.getId());
                        startActivity(new Intent(DisplayQuestionActivity.this,DisplayQuestionActivity.class));
                        Toast.makeText(DisplayQuestionActivity.this,"Question Deleted.",Toast.LENGTH_LONG).show();
                    }
                });

                //Update
                builder.setNeutralButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(DisplayQuestionActivity.this,EditQuestion.class);
                        intent.putExtra("ID",String.valueOf(question5.getId()));
                        startActivity(intent);

                    }
                });

                builder.show();
            }
        });

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