package com.example.sciencecenter;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class register extends AppCompatActivity {
    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;
    Button _btnreg;
    EditText _txtfname,_txtlname,_txtpass,_txtemail,_txtphone;
    Student student;
    DatabaseReference dbRef;

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

        _btnreg = (Button)findViewById(R.id.btnreg);

        student = new Student();

        _btnreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               dbRef = FirebaseDatabase.getInstance().getReference().child("Student");
               student.setFname(_txtfname.getText().toString().trim());
               student.setLname(_txtlname.getText().toString().trim());
               student.setPassword(_txtpass.getText().toString().trim());
               student.setEmail(_txtemail.getText().toString().trim());
               student.setPhone(_txtphone.getText().toString().trim());

               dbRef.child("").setValue("Student");
                Toast.makeText(register.this,"Successfully Registered..!!!",Toast.LENGTH_LONG).show();
                clear();
            }
        });
        

    }
    public void clear(){
        _txtfname.setText("");
        _txtlname.setText("");
        _txtpass.setText("");
        _txtemail.setText("");
        _txtphone.setText("");
    }


}
