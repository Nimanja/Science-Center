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

public class NoticeFeedback extends AppCompatActivity {

//    private static final int GalleryPick = 1;

    private EditText mfbnameText, mmsgText, mPhone;
    private Button msubbtn, mview, backbtn, mClear;
    private ProgressDialog loadingBar;
//  private ImageView mImageView;


    private Uri mImageUri;


    FirebaseApp firebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_feedback);



        mfbnameText = findViewById(R.id.fbnameText);
        mPhone = findViewById(R.id.PhoneText);
        mmsgText = findViewById(R.id.msgText);
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
        String name = mfbnameText.getText().toString();
        String phone = mPhone.getText().toString();
        String comment = mmsgText.getText().toString();

        if(name.isEmpty() && phone.isEmpty() && comment.isEmpty()){
            Toast.makeText(this,"Already Empty! ",Toast.LENGTH_SHORT).show();
        }
//        else if (!name.isEmpty() || !phone.isEmpty() || !comment.isEmpty()){
//            mfbnameText.setText("");
//            mPhone.setText("");
//            mmsgText.setText("");
//        }
        else {
            mfbnameText.setText("");
            mPhone.setText("");
            mmsgText.setText("");
        }
    }

//    private void openFileChooser() {
//        Intent intent = new Intent();
//        intent.setAction(Intent.ACTION_GET_CONTENT);
//        intent.setType("image/*");
//        startActivityForResult(intent, GalleryPick);
//    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == GalleryPick && requestCode == RESULT_OK && data != null) {
//            mImageUri = data.getData();
//            mImageView.setImageURI(mImageUri);
//
////            Picasso.get().load(mImageUri).into(mImageView); // we can Do this type View Image but this is Better than This type ---> mImageView.setImageURI(mImageUri);
//
//        }
//    }

    private void SubmitBtn() {
        String name = mfbnameText.getText().toString();
        String phone = mPhone.getText().toString();
        String comment = mmsgText.getText().toString();
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "Enter your name", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(phone)) {
            Toast.makeText(this, "Enter your phone", Toast.LENGTH_SHORT).show();
        } else if (phone.length() <10 && phone.length() > 10){
            Toast.makeText(this ,"plz Enter valid phone number",Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(comment)) {
            Toast.makeText(this, "Enter your Comment", Toast.LENGTH_SHORT).show();
        } else {
            loadingBar.setTitle("Feedback");
            loadingBar.setMessage("Please wait, Save your Feedback");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();

            validatephone(name, phone, comment);
        }
    }


    private void validatephone(final String name, final String phone, final String comment) {
        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        System.out.println(RootRef.toString());
        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (!(dataSnapshot.child("Users").child(phone).exists())) {
                    HashMap<String, Object> userdateMap = new HashMap<>();
                    userdateMap.put("Phone", phone);
                    userdateMap.put("name", name);
                    userdateMap.put("Comment", comment);

                    RootRef.child("FeedBack").child(phone).updateChildren(userdateMap)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(NoticeFeedback.this, "Feedback is successful Thank you", Toast.LENGTH_SHORT).show();
                                        loadingBar.dismiss();

//                                           Intent i = new Intent(NoticeFeedback.this,View_Feedback.class);
//                                            startActivity(i);

                                    } else {
                                        loadingBar.dismiss();
                                        Toast.makeText(NoticeFeedback.this, "Network Error", Toast.LENGTH_SHORT).show();

                                    }

                                }
                            });
                } else {
                    Toast.makeText(NoticeFeedback.this, "This" + phone + "already Exists", Toast.LENGTH_SHORT).show();
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
                                .setMessage("Name - " + name + "\n\n" + "Phone - " + phone + "\n\n" + "Comment - " + comment)
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