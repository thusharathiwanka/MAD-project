package com.example.mad_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Admin_lesson_PHP extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_lesson_php);

        TextView L1 = findViewById(R.id.L1);
        L1.setText(R.string.Les1);

        TextView L2 = findViewById(R.id.L2);
        L2.setText(R.string.Les2);

        TextView m1 = findViewById(R.id.m1);
        m1.setText(R.string.ma1);

        TextView m2 = findViewById(R.id.m2);
        m2.setText(R.string.ma2);

        TextView m3 = findViewById(R.id.m3);
        m3.setText(R.string.ma3);

        TextView m4 = findViewById(R.id.m4);
        m4.setText(R.string.ma4);

        TextView m5 = findViewById(R.id.m5);
        m5.setText(R.string.ma5);

        Button button10 = findViewById(R.id.button10);
        button10.setText("+");
    }

    public void goAddLesson(View v) {
        Intent i = new Intent(this, Admin_add_lesson.class);
        startActivity(i);
    }
}