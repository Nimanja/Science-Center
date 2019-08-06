package com.example.sciencecenter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.service.autofill.OnClickAction;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static int SPLASH_TIME_OUT = 5000; //This is 8 seconds

    Button noticesBtn, classBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        noticesBtn = findViewById(R.id.btnN);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent HomeInten = new Intent(MainActivity.this,Home.class);
                startActivity(HomeInten);
                finish();
            }
        },SPLASH_TIME_OUT);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.btnN:
                getSupportFragmentManager().beginTransaction().replace(R.id.defaultFragment, new NoticesHome()).commit();

        }
    }
}
