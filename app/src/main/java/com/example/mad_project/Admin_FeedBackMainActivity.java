package com.example.mad_project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class Admin_FeedBackMainActivity extends AppCompatActivity {

    private Button add;
    private ListView listView;
    private TextView count;
    Context context;
    private FeedBackDbHandler dbHandler;
    private List<FeedBack> feedBacks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_feed_back_main);
        context = this;

        dbHandler = new FeedBackDbHandler(context);

        listView = findViewById(R.id.feedbacklist);

        feedBacks = new ArrayList<>();

        feedBacks = dbHandler.getAllFeedBacks();

        FeedBackAdapter adapter = new FeedBackAdapter(context,R.layout.single_admin_feedback,feedBacks);

        listView.setAdapter(adapter);

        //get feedback counts from the table

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                final FeedBack feedback = feedBacks.get(position);
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle(feedback.getTitle());
                builder.setMessage(feedback.getDescription());

                builder.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dbHandler.deleteFeedBack(feedback.getId());
                        startActivity(new Intent(context,Admin_FeedBackMainActivity.class));
                    }
                });

                builder.show();
            }
        });

    }
}
