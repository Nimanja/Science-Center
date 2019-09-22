package com.example.sciencecenter;

import android.app.ProgressDialog;
import android.os.Bundle;
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

public class AdminView extends AppCompatActivity {

    private Button btnaddAD, btnReset, btnView;
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
        setContentView( R.layout.activity_admin_view );

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

        std = new AdminNotices();




    }

    private void view() {

        DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child( "Notices" ).push();
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
}
