package com.example.sciencecenter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.sciencecenter.ui.fragmentclass.FragmentClassFragment;

public class fragment_class extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_class_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, FragmentClassFragment.newInstance())
                    .commitNow();
        }

    }
}
