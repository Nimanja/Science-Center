package com.example.sciencecenter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Notices extends AppCompatActivity {

    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notices);

        button = findViewById(R.id.btnback);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Home.class);
                startActivity(i);
            }
        });
        button = findViewById(R.id.btnH);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Home.class);
                startActivity(i);
            }
        });
        button = findViewById(R.id.btnA);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Attendees.class);
                startActivity(i);
            }
        });
        button = findViewById(R.id.btnP);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Payment.class);
                startActivity(i);
            }
        });
        button = findViewById(R.id.btnN);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Notices.class);
                startActivity(i);
            }
        });

        button = findViewById(R.id.btnpf);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Profile.class);
                startActivity(i);
            }
        });

        button = findViewById(R.id.btnclz);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),ClassInside.class);
                startActivity(i);
            }
        });
        button =findViewById(R.id.btnFeedback);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), NoticeFeedback.class);
                startActivity(i);
            }
        });
        button = findViewById(R.id.ViewFB);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), View_Feedback.class);
                startActivity(i);
            }
        });
    }
}
