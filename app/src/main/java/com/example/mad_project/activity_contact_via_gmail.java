package com.example.mad_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;

public class activity_contact_via_gmail extends AppCompatActivity {
    Button btn1;
    EditText maintxt;
    
    MultiAutoCompleteTextView subtxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_via_gmail);
        maintxt =findViewById (R.id.subject);
        subtxt = findViewById(R.id.body);
        btn1= findViewById(R.id.send);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(Intent.ACTION_SEND);
                String main_message = maintxt.getText().toString();
                String sub_message = subtxt.getText().toString();
                i.setData(Uri.parse("mailto:"));
                i.putExtra(i.EXTRA_EMAIL,new String[]{"codelearner@learn.com"});
                i.putExtra(i.EXTRA_SUBJECT, main_message);
                i.putExtra(i.EXTRA_TEXT,sub_message);
                i.setType("text/plane");
                final Intent chooser=Intent.createChooser(i,"Send mail by this app");

                startActivity(chooser);
            }
        });

    }
}