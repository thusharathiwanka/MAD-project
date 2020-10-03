package com.example.mad_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class AddFeedBack extends AppCompatActivity {

    private EditText title, desc;
    private Button add;
    private FeedBackDbHandler dbHandler;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_feed_back);

        title = findViewById(R.id.editTextTitle);
        desc = findViewById(R.id.editTextDescription);
        add = findViewById(R.id.buttonAdd);
        context = this;

        dbHandler = new FeedBackDbHandler(context);


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(title.getText())) {

                    title.setError("Title is required!");
                }
                else if(TextUtils.isEmpty(desc.getText())){
                    desc.setError("Description is required!");
                }
                else {
                    String userTitle = title.getText().toString();
                    String userDesc = desc.getText().toString();
                    long started = System.currentTimeMillis();

                    FeedBack feedBack = new FeedBack(userTitle, userDesc, started, 0);
                    dbHandler.addFeedBack(feedBack);

                    startActivity(new Intent(context, FeedBackMainActivity.class));
                }
            }

        });
    }
}