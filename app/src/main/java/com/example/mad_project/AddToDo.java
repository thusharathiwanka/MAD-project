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

public class AddToDo extends AppCompatActivity {

    private EditText title, desc;
    private Button add;
    private ToDoDbHandler dbHandler;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_do);

        title = findViewById(R.id.editTextTitle);
        desc = findViewById(R.id.editTextDescription);
        add = findViewById(R.id.buttonAdd);
        context = this;

        dbHandler = new ToDoDbHandler(context);


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(title.getText())) {

                    title.setError("Title is required!");
                } else {

                    String userTitle = title.getText().toString();
                    String userDesc = desc.getText().toString();
                    long started = System.currentTimeMillis();

                    ToDo toDo = new ToDo(userTitle, userDesc, started, 0);
                    dbHandler.addToDo(toDo);

                    startActivity(new Intent(context, ToDoMainActivity.class));
                }
            }
        });
    }
}