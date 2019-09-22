package com.example.sciencecenter;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
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

public class AdminNotices extends AppCompatActivity {

    //    private static final int GalleryPick = 1;

    private EditText msubname, mGrade, mcomment;
    private Button msubbtn, mview, backbtn, mClear;
    private ProgressDialog loadingBar;
//  private ImageView mImageView;


    private Uri mImageUri;

    FirebaseApp firebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_notices);
        msubname = findViewById(R.id.subname);
        mGrade = findViewById(R.id.Grade);
        mcomment = findViewById(R.id.comment);
        msubbtn = findViewById(R.id.subbtn);
        mview = findViewById(R.id.fbview);
//        mChooseImg = findViewById(R.id.ChooseImg);
        mClear = findViewById(R.id.Clear);
        backbtn = findViewById(R.id.btnback);
//        mImageView = findViewById(R.id.UploadImageView);

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

        mClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Clear();
            }
        });

//        mChooseImg.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openFileChooser();
//            }
//        });

        msubbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SubmitBtn();
            }
        });

    }

    private void Clear() {
        String subject = msubname.getText().toString();
        String grade = mGrade.getText().toString();
        String comment = mcomment.getText().toString();

        if(subject.isEmpty() && grade.isEmpty() && comment.isEmpty()){
            Toast.makeText(this,"Already Empty! ",Toast.LENGTH_SHORT).show();
        }

        else {
            msubname.setText("");
            mGrade.setText("");
            mcomment.setText("");
        }
    }

    private void SubmitBtn() {
        String Subject = msubname.getText().toString();
        String Grade = mGrade.getText().toString();
        String comment = mcomment.getText().toString();
        if (TextUtils.isEmpty(Subject)) {
            Toast.makeText(this, "Enter your subject", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(Grade)) {
            Toast.makeText(this, "Enter your Grade", Toast.LENGTH_SHORT).show();
        }else if (TextUtils.isEmpty(comment)) {
            Toast.makeText(this, "Enter your Comment", Toast.LENGTH_SHORT).show();
        } else {
            loadingBar.setTitle("Feedback");
            loadingBar.setMessage("Please wait, Save your Feedback");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();

            validatephone(Subject, Grade, comment);
        }
    }


    private void validatephone(final String Subject, final String Grade, final String comment) {
        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        System.out.println(RootRef.toString());
        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (!(dataSnapshot.child("Users").child(Grade).exists())) {
                    HashMap<String, Object> userdateMap = new HashMap<>();
                    userdateMap.put("Grade", Grade);
                    userdateMap.put("Subject", Subject);
                    userdateMap.put("Comment", comment);

                    RootRef.child("Notices").child(Grade).updateChildren(userdateMap)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(AdminNotices.this, "Feedback is successful Thank you", Toast.LENGTH_SHORT).show();
                                        loadingBar.dismiss();

//                                           Intent i = new Intent(NoticeFeedback.this,View_Feedback.class);
//                                            startActivity(i);

                                    } else {
                                        loadingBar.dismiss();
                                        Toast.makeText(AdminNotices.this, "Network Error", Toast.LENGTH_SHORT).show();

                                    }

                                }
                            });
                } else {
                    Toast.makeText(AdminNotices.this, "This" + Grade + "already Exists", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                    Toast.makeText(AdminNotices.this, "Please Try again using another email Address", Toast.LENGTH_SHORT).show();
//                        Intent i = new Intent(NoticeFeedback.this,Notices.class);
//                        startActivity(i);

                }
                mview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        new AlertDialog.Builder(AdminNotices.this)
                                .setTitle("Send Details: ")
                                .setMessage("Name - " + Subject + "\n\n" + "Phone - " + Grade + "\n\n" + "Comment - " + comment)
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