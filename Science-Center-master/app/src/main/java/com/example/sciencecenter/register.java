package com.example.sciencecenter;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class register extends AppCompatActivity {
    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;
    Button _btnreg;
    EditText _txtfname,_txtlname,_txtpass,_txtemail,_txtphone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        _btnreg = (Button)findViewById(R.id.btnreg);
        _txtfname = (EditText)findViewById(R.id.txtfname);
        _txtlname = (EditText)findViewById(R.id.txtlname);
        _txtpass = (EditText)findViewById(R.id.txtpass);
        _txtemail = (EditText)findViewById(R.id.txtemail);
        _txtphone = (EditText)findViewById(R.id.txtphone);

        _btnreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db=openHelper.getWritableDatabase();
                String fname = _txtfname.getText().toString();
                String lname = _txtlname.getText().toString();
                String pass = _txtpass.getText().toString();
                String email = _txtemail.getText().toString();
                String phone = _txtphone.getText().toString();
                insertdata(fname,lname,pass,email,phone);
                Toast.makeText(getApplicationContext(),"Register successfully",Toast.LENGTH_LONG).show();

            }
        });
    }
    public void insertdata(String fname,String lname,String pass,String email,String phone){
        ContentValues contentValues = new ContentValues();
    }
}
