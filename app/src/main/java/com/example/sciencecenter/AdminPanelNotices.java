package com.example.sciencecenter;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AdminPanelNotices extends AppCompatActivity {

    private String Subject,Grade,Teacher;
    private Button mAdd;
    private ImageView mUploadImg;
    private EditText mSubject, mGrade, mTeacher;
    private static final int GalleryPick = 1;
    private Uri ImageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_panel_notices);

        mAdd = (Button) findViewById(R.id.AddAD);
        mUploadImg = (ImageView) findViewById(R.id.UploadImg);
        mSubject = (EditText) findViewById(R.id.Subject);
        mGrade = (EditText) findViewById(R.id.Grade);
        mTeacher = (EditText) findViewById(R.id.Teacher);

        mUploadImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenGallery();
            }
        });
        mAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ValidateAd();
            }
        });
    }


    private void OpenGallery() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, GalleryPick);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GalleryPick && requestCode == RESULT_OK && data != null) {
            ImageUri = data.getData();
            mUploadImg.setImageURI(ImageUri);

//            Picasso.get().load(mImageUri).into(mImageView); // we can Do this type View Image but this is Better than This type ---> mImageView.setImageURI(mImageUri);

        }
    }

    private void ValidateAd() {

        Subject = mSubject.getText().toString();
        Grade = mGrade.getText().toString();
        Teacher = mTeacher.getText().toString();

        if(ImageUri == null){
            Toast.makeText(this,"Image is No there...",Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(Subject)){
            Toast.makeText(this,"please Enter Subject...",Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(Grade)){
            Toast.makeText(this,"please Enter Grade...",Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(Teacher)){
            Toast.makeText(this,"please Enter Teacher...",Toast.LENGTH_SHORT).show();
        }
        else{
            StoreImformation();
        }
    }

    private void StoreImformation() {

    }
}
