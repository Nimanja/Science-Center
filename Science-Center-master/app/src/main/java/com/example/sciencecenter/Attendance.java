package com.example.sciencecenter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class Attendance extends AppCompatActivity  {

    Button buttonBack,buttonH,buttonA,buttonP,buttonN,buttonPf, buttonDate,buttonSubOk, buttonSubmit;
    TextView tvDate, tvSubject, tvStatus;
    EditText etStdID;
    Spinner spinner;

    Calendar c;
    DatePickerDialog dpd;

    String subjects[] = {"Choose Sub","Maths", "Biology", "Chemistry", "Physics", "ICT"};
    String record= "";
    //define array adapter od String type
    ArrayAdapter <String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);

        buttonDate = findViewById(R.id.BtnDate);
        buttonSubmit = findViewById(R.id.BtnSubmit);
        buttonSubOk = findViewById(R.id.BtnSubOK);
        buttonBack = findViewById(R.id.btnback);
        buttonH = findViewById(R.id.btnH);
        buttonA = findViewById(R.id.btnA);
        buttonP = findViewById(R.id.btnP);
        buttonN = findViewById(R.id.btnN);
        buttonPf = findViewById(R.id.btnpf);
        tvSubject = findViewById(R.id.TvSubject);
        tvDate = findViewById(R.id.TvDate);
        tvStatus = findViewById(R.id.TvStatus);
        spinner = findViewById(R.id.Spin2);
        etStdID = findViewById(R.id.EtStdID);


        //DatePicker
        buttonDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                c = Calendar.getInstance();
                int day = c.get(Calendar.DAY_OF_MONTH);
                int month = c.get(Calendar.MONTH);
                int year = c.get(Calendar.YEAR);

                dpd = new DatePickerDialog(Attendance.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int mYear, int mMonth, int mDay) {
                        tvDate.setText(mDay + "/" + (mMonth+1) + "/" + mYear);

                    }
                },day,month,year);
                dpd.show();;

            }
        });//end_Of_Date_Picker



        //Spinner
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, subjects);

        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch (position)
                {
                    case 0:
                        record = "Choose Sub";
                        break;

                    case 1:
                        record = "Maths";
                        break;

                    case 2:
                        record = "Biology";
                        break;

                    case 3:
                        record = "Chemistry";
                        break;

                    case 4:
                        record = "Physics";
                        break;

                    case 5:
                        record = "ICT";
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });//end Of Spinner

        buttonH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Home.class);
                startActivity(i);
            }
        });

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Home.class);
                startActivity(i);
            }
        });

        buttonA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Attendance.class);
                startActivity(i);
            }
        });

        buttonP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Payment.class);
                startActivity(i);
            }
        });

        buttonN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Notices.class);
                startActivity(i);
            }
        });

        buttonPf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Profile.class);
                startActivity(i);
            }
        });

    }
    public void displaySubList(View view){

        tvSubject.setText(record);
    }

    //SUBMIT METHOD
    public void submit(View view){
        tvStatus.setText("");
       try {
           if (TextUtils.isEmpty(etStdID.getText().toString()))
               Toast.makeText(getApplicationContext(),"Please enter an ID", Toast.LENGTH_SHORT).show();
           else if (TextUtils.isEmpty(tvSubject.getText().toString()))
               Toast.makeText(getApplicationContext(), "Please choose the Subject", Toast.LENGTH_SHORT).show();
           else if (TextUtils.isEmpty(tvDate.getText().toString()))
               Toast.makeText(getApplicationContext(), "Please Select the Date", Toast.LENGTH_SHORT).show();
           else {
               if (tvSubject.getText() == "Maths") {
                   DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("MathsTB").child(etStdID.getText().toString().trim());
                   readRef.addListenerForSingleValueEvent(new ValueEventListener() {
                       @Override
                       public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                           if (dataSnapshot.hasChildren()) {
                               tvStatus.setText(dataSnapshot.child("name").getValue().toString() + "   ->    " + dataSnapshot.child("attendance").getValue().toString());

                           } else
                               Toast.makeText(getApplicationContext(), "No Source to Display", Toast.LENGTH_SHORT).show();

                       }


                       @Override
                       public void onCancelled(@NonNull DatabaseError databaseError) {

                       }
                   });
               }
               if (tvSubject.getText() == "Physics") {
                   DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("PhysicsTB").child(etStdID.getText().toString().trim());
                   readRef.addListenerForSingleValueEvent(new ValueEventListener() {
                       @Override
                       public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                           if (dataSnapshot.hasChildren()) {
                               tvStatus.setText(dataSnapshot.child("name").getValue().toString() + "   ->    " + dataSnapshot.child("attendance").getValue().toString());

                           } else
                               Toast.makeText(getApplicationContext(), "No Source to Display", Toast.LENGTH_SHORT).show();

                       }


                       @Override
                       public void onCancelled(@NonNull DatabaseError databaseError) {

                       }
                   });
               }
               if (tvSubject.getText() == "Chemistry") {
                   DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("ChemistryTB").child(etStdID.getText().toString().trim());
                   readRef.addListenerForSingleValueEvent(new ValueEventListener() {
                       @Override
                       public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                           if (dataSnapshot.hasChildren()) {
                               tvStatus.setText(dataSnapshot.child("name").getValue().toString() + "   ->    " + dataSnapshot.child("attendance").getValue().toString());

                           } else
                               Toast.makeText(getApplicationContext(), "No Source to Display", Toast.LENGTH_SHORT).show();

                       }


                       @Override
                       public void onCancelled(@NonNull DatabaseError databaseError) {

                       }
                   });
               }
               if (tvSubject.getText() == "Biology") {
                   DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("BioTB").child(etStdID.getText().toString().trim());
                   readRef.addListenerForSingleValueEvent(new ValueEventListener() {
                       @Override
                       public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                           if (dataSnapshot.hasChildren()) {
                               tvStatus.setText(dataSnapshot.child("name").getValue().toString() + "   ->    " + dataSnapshot.child("attendance").getValue().toString());

                           } else
                               Toast.makeText(getApplicationContext(), "No Source to Display", Toast.LENGTH_SHORT).show();

                       }


                       @Override
                       public void onCancelled(@NonNull DatabaseError databaseError) {

                       }
                   });
               }
               if (tvSubject.getText() == "ICT") {
                   DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("ICT_TB").child(etStdID.getText().toString().trim());
                   readRef.addListenerForSingleValueEvent(new ValueEventListener() {
                       @Override
                       public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                           if (dataSnapshot.hasChildren()) {
                               tvStatus.setText(dataSnapshot.child("name").getValue().toString() + "   ->    " + dataSnapshot.child("attendance").getValue().toString());

                           } else
                               Toast.makeText(getApplicationContext(), "No Source to Display", Toast.LENGTH_SHORT).show();

                       }


                       @Override
                       public void onCancelled(@NonNull DatabaseError databaseError) {

                       }
                   });
               }
           }
       }
       catch (NumberFormatException e){
           Toast.makeText(getApplicationContext(), "Invalid ID", Toast.LENGTH_SHORT).show();
       }
    }

}

