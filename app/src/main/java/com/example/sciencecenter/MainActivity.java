package com.example.sciencecenter;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 5000; //This is 5 seconds

    Button noticesBtn, classBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        noticesBtn = findViewById(R.id.btnN);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent HomeInten = new Intent(MainActivity.this, Login.class);
                startActivity(HomeInten);
                finish();
            }
        }, 3000);
    }

}

