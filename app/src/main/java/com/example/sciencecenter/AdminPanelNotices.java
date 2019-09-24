package com.example.sciencecenter;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AdminPanelNotices extends AppCompatActivity {

    private Button btnaddAD, btnReset, btnView,btnUpdata;
    private EditText txtSubject, txtTeacher, txtGrade, txtCallHall, txtDate, txtStartTime, txtEndTime;
    private ProgressDialog loadingBar;

    DatabaseReference dbRef;

    AdminNotices std;

    private void clearControls() {
        txtSubject.setText( "" );
        txtTeacher.setText( "" );
        txtGrade.setText( "" );
        txtCallHall.setText( "" );
        txtDate.setText( "" );
        txtStartTime.setText( "" );
        txtEndTime.setText( "" );
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_admin_panel_notices );

        txtSubject = findViewById( R.id.Subject );
        txtTeacher = findViewById( R.id.Teacher );
        txtGrade = findViewById( R.id.Grade );
        txtCallHall = findViewById( R.id.HallNo );
        txtDate = findViewById( R.id.Date );
        txtStartTime = findViewById( R.id.StartTime );
        txtEndTime = findViewById( R.id.EndTime );

        btnaddAD = findViewById( R.id.addAD );
        btnReset = findViewById( R.id.Reset );
        btnView = findViewById( R.id.View );
        btnUpdata = findViewById(R.id.Updata);

        std = new AdminNotices();

        btnUpdata.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnUpdata();
            }
        } );

        btnReset.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Clear();
            }
        } );

        btnaddAD.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Addbtn();
            }
        } );

        btnView.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view();
            }
        } );

    }

    private void Addbtn() {
        dbRef = FirebaseDatabase.getInstance().getReference().child( "AdminNotices" );

        try {
            if (TextUtils.isEmpty( txtSubject.getText().toString() ))
                Toast.makeText( getApplicationContext(), "Pleace Enter Subject", Toast.LENGTH_SHORT ).show();
            else if (TextUtils.isEmpty( txtGrade.getText().toString() ))
                Toast.makeText( getApplicationContext(), "Pleace Enter Grade", Toast.LENGTH_SHORT ).show();
            else if (TextUtils.isEmpty( txtTeacher.getText().toString() ))
                Toast.makeText( getApplicationContext(), "Pleace Enter Teacher", Toast.LENGTH_SHORT ).show();
            else if (TextUtils.isEmpty( txtCallHall.getText().toString() ))
                Toast.makeText( getApplicationContext(), "Pleace Enter HallNumber", Toast.LENGTH_SHORT ).show();
            else if (TextUtils.isEmpty( txtStartTime.getText().toString() ))
                Toast.makeText( getApplicationContext(), "Pleace Enter Start Time", Toast.LENGTH_SHORT ).show();
            else if (TextUtils.isEmpty( txtEndTime.getText().toString() ))
                Toast.makeText( getApplicationContext(), "Pleace Enter End Time", Toast.LENGTH_SHORT ).show();
            else if (TextUtils.isEmpty( txtDate.getText().toString() ))
                Toast.makeText( getApplicationContext(), "Pleace Enter Date", Toast.LENGTH_SHORT ).show();
            else {
                std.setSubject( txtSubject.getText().toString().trim() );
                std.setGrade( txtGrade.getText().toString().trim() );
                std.setTeacher( txtTeacher.getText().toString().trim() );
                std.setCallHall( txtCallHall.getText().toString().trim() );
                std.setStartTime( txtStartTime.getText().toString().trim() );
                std.setEndTime( txtEndTime.getText().toString().trim() );
                std.setDate( txtDate.getText().toString().trim() );

                dbRef.child("Std1").setValue( std );

                Toast.makeText( getApplicationContext(), "Add data Saved Successfully", Toast.LENGTH_SHORT ).show();
                clearControls();
            }
        } catch (NumberFormatException e) {
            Toast.makeText( getApplicationContext(), "Invalide Contact Number", Toast.LENGTH_SHORT ).show();
        }


    }

    private void view() {

        DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("AdminNotices").child("Std1");
        readRef.addListenerForSingleValueEvent( new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChildren()) {
                    txtSubject.setText( dataSnapshot.child( "Subject" ).getValue().toString() );
                    txtGrade.setText( dataSnapshot.child( "Grade" ).getValue().toString() );
                    txtTeacher.setText( dataSnapshot.child( "Teacher" ).getValue().toString() );
                    txtCallHall.setText( dataSnapshot.child( "HallNo" ).getValue().toString() );
                    txtDate.setText( dataSnapshot.child( "Date" ).getValue().toString() );
                    txtStartTime.setText( dataSnapshot.child( "StartTime" ).getValue().toString() );
                    txtEndTime.setText( dataSnapshot.child( "EndTime" ).getValue().toString() );
                } else
                    Toast.makeText( getApplicationContext(), "No Source to Display", Toast.LENGTH_SHORT ).show();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        } );
    }

    private void btnUpdata() {

        DatabaseReference updRef = FirebaseDatabase.getInstance().getReference().child("AdminNotices");
        updRef.addListenerForSingleValueEvent( new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild("Std1")) {
                  try {
                      txtSubject.setText( dataSnapshot.child( "Subject" ).getValue().toString() );
                      txtGrade.setText( dataSnapshot.child( "Grade" ).getValue().toString() );
                      txtTeacher.setText( dataSnapshot.child( "Teacher" ).getValue().toString() );
                      txtCallHall.setText( dataSnapshot.child( "HallNo" ).getValue().toString() );
                      txtDate.setText( dataSnapshot.child( "Date" ).getValue().toString() );
                      txtStartTime.setText( dataSnapshot.child( "StartTime" ).getValue().toString() );
                      txtEndTime.setText( dataSnapshot.child( "EndTime" ).getValue().toString() );

                      dbRef = FirebaseDatabase.getInstance().getReference().child( "Notices" ).child( "Std1" );
                      dbRef.setValue( std );
                      clearControls();
                      Toast.makeText( getApplicationContext(), "Data Updata is succes", Toast.LENGTH_SHORT ).show();
                  }catch (NumberFormatException e){
                      Toast.makeText(getApplicationContext(), "Invalid Updata",Toast.LENGTH_SHORT).show();
                  }
                  } else
                    Toast.makeText( getApplicationContext(), "No Source to Display", Toast.LENGTH_SHORT ).show();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        } );
    }

    private void Clear() {
        String Subject = txtSubject.getText().toString();
        String Teacher = txtTeacher.getText().toString();
        String Grade = txtGrade.getText().toString();
        String HallNo = txtCallHall.getText().toString();
        String Date = txtDate.getText().toString();
        String StartTime = txtStartTime.getText().toString();
        String EndTime = txtEndTime.getText().toString();


        if (Subject.isEmpty() && Teacher.isEmpty() && Grade.isEmpty() && HallNo.isEmpty() && Date.isEmpty() && StartTime.isEmpty() && EndTime.isEmpty()) {
            Toast.makeText( this, "Already Empty! ", Toast.LENGTH_SHORT ).show();
        } else {
            txtSubject.setText( "" );
            txtTeacher.setText( "" );
            txtGrade.setText( "" );
            txtCallHall.setText( "" );
            txtDate.setText( "" );
            txtStartTime.setText( "" );
            txtEndTime.setText( "" );


        }

    }
//    private void validatephone(final String Subject, final String Teacher, final String Grade,final String CallHall, final String Date, final String StartTime, final String EndTime) {
//        final DatabaseReference dbRef;
//        dbRef = FirebaseDatabase.getInstance().getReference();
//
//        System.out.println(dbRef.toString());
//        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                if (!(dataSnapshot.child("Notices").child(Subject).exists())) {
//                    HashMap<String, Object> userdateMap = new HashMap<>();
//                    userdateMap.put("Subject", Subject);
//                    userdateMap.put("Teacher", Teacher);
//                    userdateMap.put("Grade", Grade);
//                    userdateMap.put("HallNo", CallHall);
//                    userdateMap.put("StartTime", StartTime);
//                    userdateMap.put("EndTime", EndTime);
//                    userdateMap.put("Date", Date);
//
//                    dbRef.child("FeedBack").child(Subject).updateChildren(userdateMap)
//                            .addOnCompleteListener(new OnCompleteListener<Void>() {
//                                @Override
//                                public void onComplete(@NonNull Task<Void> task) {
//                                    if (task.isSuccessful()) {
//                                        Toast.makeText(AdminPanelNotices.this, "Feedback is successful Thank you", Toast.LENGTH_SHORT).show();
//                                        loadingBar.dismiss();
//
//
//                                    } else {
//                                        loadingBar.dismiss();
//                                        Toast.makeText(AdminPanelNotices.this, "Network Error", Toast.LENGTH_SHORT).show();
//
//                                    }
//
//                                }
//                            });
//                } else {
//                    Toast.makeText(AdminPanelNotices.this, "This" + Subject + "already Exists", Toast.LENGTH_SHORT).show();
//                    loadingBar.dismiss();
//                    Toast.makeText(AdminPanelNotices.this, "Please Try again using another email Address", Toast.LENGTH_SHORT).show();
//
//                }
//                btnView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        new AlertDialog.Builder(AdminPanelNotices.this)
//                                .setTitle("Send Details: ")
//                                .setMessage("Subject - " + Subject + "\n\n" + "Grade - " + Grade + "\n\n" + "Teacher - " + Teacher + "\n\n" + "HallNo - " + CallHall + "\n\n" + "StartTime - " + StartTime  + "\n\n" + "EndTime - " + EndTime + "\n\n" + "Date - " + Date)
//                                .show();
//
//                    }
//                });
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//
//        });
//    }
}
