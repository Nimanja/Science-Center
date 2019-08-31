package com.example.sciencecenter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ReportFragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.sciencecenter.ui.fragmentclass.FragmentClassFragment;

public class fragment_class extends AppCompatActivity {

    View v;
    Button classBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_class_activity);


    }

}
