package com.example.sciencecenter;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class NoticeFeedback extends AppCompatActivity {

    private EditText mfbnameText, mmsgText, memailText;
    private Button msubbtn, mcambtn, mview, backbtn;
    private ProgressDialog loadingBar;

    FirebaseApp firebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_feedback);

        mfbnameText = findViewById(R.id.fbnameText);
        memailText = findViewById(R.id.emailText);
        mmsgText = findViewById(R.id.msgText);
        msubbtn = findViewById(R.id.subbtn);
        mview = findViewById(R.id.fbview);
        mcambtn = findViewById(R.id.cambtn);
        backbtn = findViewById(R.id.btnback);

        loadingBar = new ProgressDialog(this);

        String UniquesID = Settings.Secure.getString(getApplicationContext().getContentResolver(),
                Settings.Secure.ANDROID_ID);

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(), Notices.class);
                startActivity(i);

            }
        });



        msubbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SubmitBtn();
            }
        });

    }

    private void SubmitBtn() {
        String name = mfbnameText.getText().toString();
        String email = memailText.getText().toString();
        String comment = mmsgText.getText().toString();
        if(TextUtils.isEmpty(name)){
            Toast.makeText(this,"Enter your name",Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Enter your Email",Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(comment)){
            Toast.makeText(this,"Enter your Comment",Toast.LENGTH_SHORT).show();
        }
        else {
            loadingBar.setTitle("Feedback");
            loadingBar.setMessage("Please wait, Save your Feedback");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();

            validateEmail(name,email,comment);
        }
    }



    private void validateEmail(final String name, final String email, final String comment) {
            final DatabaseReference RootRef;
            RootRef = FirebaseDatabase.getInstance().getReference();

        System.out.println(RootRef.toString());
            RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (!(dataSnapshot.child("Users").child(email).exists())){
                        HashMap<String,Object> userdateMap = new HashMap<>();
                        userdateMap.put("Email",email);
                        userdateMap.put("name",name);
                        userdateMap.put("Comment",comment);

                        RootRef.child("FeedBack").child(email).updateChildren(userdateMap)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful()){
                                            Toast.makeText(NoticeFeedback.this,"Feedback is successful Thank you",Toast.LENGTH_SHORT).show();
                                            loadingBar.dismiss();

//                                            Intent i = new Intent(NoticeFeedback.this,Notices.class);
//                                            startActivity(i);

                                        }
                                        else {
                                            loadingBar.dismiss();
                                            Toast.makeText(NoticeFeedback.this,"Network Error",Toast.LENGTH_SHORT).show();

                                        }

                                    }
                                });
                    }
                    else {
                        Toast.makeText( NoticeFeedback.this, "This" + email + "already Exists",Toast.LENGTH_SHORT).show();
                        loadingBar.dismiss();
                        Toast.makeText(NoticeFeedback.this, "Please Try again using another email Address", Toast.LENGTH_SHORT).show();
//                        Intent i = new Intent(NoticeFeedback.this,Notices.class);
//                        startActivity(i);

                    }
                    mview.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            new AlertDialog.Builder(NoticeFeedback.this)
                                    .setTitle("Send Details: ")
                                    .setMessage("Name - " + name + "\n\n" + "Email - " + email + "\n\n" + "Comment - " + comment)
                                    .show();
                        }
                    });
                    }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }

        });
    }
}