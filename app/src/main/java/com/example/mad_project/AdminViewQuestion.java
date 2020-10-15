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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AdminViewQuestion extends AppCompatActivity {

    ArrayAdapter questionArrayAdapter;
    //private Button button;
    ImageView backBtn;
    private TextView textView1;
    private Button replyBtn;
    private ListView listView1;
    private questionDB dbhandler;
    private List<Question> list3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_question);

        dbhandler = new questionDB(this);

        textView1 = findViewById(R.id.countQuestion);
        listView1 = findViewById(R.id.questionID);
        backBtn = findViewById(R.id.back);
        replyBtn = findViewById(R.id.replyBtn);
        //button = findViewById(R.id.replyBtn);

        //count
        int count = dbhandler.countQuestion();
        list3 = new ArrayList<>();
        list3 = dbhandler.getAllData();

        textView1.setText("You have "+count+" questions.");


       /*button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity2();
            }
        });*/

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        replyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),ShowAnswerActivity.class);
                startActivity(intent);
            }
        });

        showQuestionOnListView(listView1);

        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int possition3, long l) {
                final Question question6 = list3.get(possition3);
                AlertDialog.Builder builder2 = new AlertDialog.Builder(AdminViewQuestion.this);

                builder2.setTitle(question6.getModule());
                builder2.setMessage(question6.getQuestion());

                //Cancel
                builder2.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startActivity(new Intent(AdminViewQuestion.this,AdminViewQuestion.class));
                    }
                });

                //Delete
                builder2.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dbhandler.deleteQuestions(question6.getId());
                        startActivity(new Intent(AdminViewQuestion.this,AdminViewQuestion.class));
                        Toast.makeText(AdminViewQuestion.this,"Question Deleted.",Toast.LENGTH_LONG).show();
                    }
                });

                //Update
                builder2.setNeutralButton("Reply", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(AdminViewQuestion.this,ReplyQuestionActivity.class);
                        intent.putExtra("ID",String.valueOf(question6.getId()));
                        startActivity(intent);
                    }
                });
                builder2.show();
            }
        });

    }
    public void openActivity2(){
        Intent intent = new Intent(this, ShowAnswerActivity.class);
        startActivity(intent);
    }

    public void showQuestionOnListView(ListView listView2){
        questionArrayAdapter = new ArrayAdapter<Question>(AdminViewQuestion.this,android.R.layout.simple_list_item_1,dbhandler.getAllData());
        listView2.setAdapter(questionArrayAdapter);
    }

}