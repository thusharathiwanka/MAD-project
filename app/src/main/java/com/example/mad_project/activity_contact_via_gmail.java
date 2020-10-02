package com.example.mad_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Toast;

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

                if (TextUtils.isEmpty(maintxt.getText())) {

                    maintxt.setError("Subject is required!");
                }
                else if(TextUtils.isEmpty(subtxt.getText())){
                    subtxt.setError("Body paragraph is required!");
                }
                else {
                    ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                    if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                        connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {

                    Intent i = new Intent(Intent.ACTION_SEND);
                    String main_message = maintxt.getText().toString();
                    String sub_message = subtxt.getText().toString();
                    i.setData(Uri.parse("mailto:"));
                    i.putExtra(i.EXTRA_EMAIL, new String[]{"codelearner@learn.com"});
                    i.putExtra(i.EXTRA_SUBJECT, main_message);
                    i.putExtra(i.EXTRA_TEXT, sub_message);
                    i.setType("text/plane");
                    final Intent chooser = Intent.createChooser(i, "Send mail by this app");
                    startActivity(chooser);

                } else {
                    Toast.makeText(getApplicationContext(), "internet connection is not available", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(WifiManager.ACTION_PICK_WIFI_NETWORK));
                }

            } }});

        }
    }