package com.example.sciencecenter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;

public class NoticeFeedback extends AppCompatActivity {


    private Button backbtn;
    private EditText mfbnameText;
    private EditText mmsgText;
    private Button msubbtn;
    private Button mcambtn;
    private Button mview;
    private FirebaseApp firebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_feedback);

        mfbnameText = findViewById(R.id.fbnameText);
        mmsgText = findViewById(R.id.msgText);
        msubbtn = findViewById(R.id.subbtn);
        mview = findViewById(R.id.fbview);
        mcambtn = (Button) findViewById(R.id.cambtn);
        backbtn = (Button) findViewById(R.id.btnback);

        FirebaseApp.getApps(this);

        msubbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = mfbnameText.getText().toString();
                String comment = mmsgText.getText().toString();

            }
        });

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(), Notices.class);
                startActivity(i);

            }
        });

    }
}