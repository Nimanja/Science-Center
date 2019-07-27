package com.example.sciencecenter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static com.example.sciencecenter.R.id.txtview_register;

public class Login extends AppCompatActivity {
    EditText txtusername;
    EditText txtpassword;
    Button btnlogin;
    TextView txtclickhere;


    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

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

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtusername = (EditText)findViewById(R.id.txtview_username);
        txtpassword = (EditText)findViewById(R.id.txtview_password);
        btnlogin = (Button)findViewById(R.id.btnlogin);
        txtclickhere = (TextView)findViewById(txtview_register);


    }

}
