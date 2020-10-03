package com.example.mad_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditToDo extends AppCompatActivity {

    private EditText title,description;
    private Button edit;
    private ToDoDbHandler dbHandler;
    private Context context;
    private Long updateDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_to_do);

        context = this;
        dbHandler = new ToDoDbHandler(context);

        title = findViewById(R.id.editToDoTextTitle);
        description = findViewById(R.id.editToDoTextDescription);
        edit = findViewById(R.id.buttonEdit);

        final String id = getIntent().getStringExtra("id");
        ToDo todo = dbHandler.getSingleToDo(Integer.parseInt(id));

        title.setText(todo.getTitle());
        description.setText(todo.getDescription());

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(title.getText())) {

                    title.setError("Title is required!");
                } else {
                    String titleText = title.getText().toString();
                    String decText = description.getText().toString();
                    updateDate = System.currentTimeMillis();

                    ToDo toDo = new ToDo(Integer.parseInt(id), titleText, decText, updateDate, 0);
                    int state = dbHandler.updateSingleToDo(toDo);
                    System.out.println(state);
                    startActivity(new Intent(context, ToDoMainActivity.class));
                }
            }
        });
    }
}
