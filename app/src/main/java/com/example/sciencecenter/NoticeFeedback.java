package com.example.sciencecenter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class NoticeFeedback extends AppCompatActivity {


    private Button backbtn;
    private EditText mfbnameText;
    private EditText mmsgText;
    private Button msubbtn;
    private Button mcambtn;
    private StorageReference mStorage;
    private static final int GALLERY_INTENT = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_feedback);

        mStorage = FirebaseStorage.getInstance().getReference();

        mcambtn = (Button) findViewById(R.id.cambtn);
        mfbnameText = (EditText) findViewById(R.id.fbnameText);
        mmsgText = (EditText) findViewById(R.id.msgText);
        msubbtn = (Button) findViewById(R.id.subbtn);
        backbtn = (Button) findViewById(R.id.btnback);


        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(), Notices.class);
                startActivity(i);

            }
        });

        mcambtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, GALLERY_INTENT);
            }
        });
    }

    protected void onCreate() {
    }
}