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

public class Payment extends AppCompatActivity {

    Button buttonBack,buttonH,buttonA,buttonP,buttonN,buttonPf,btnOK, btnDate, btnSubmit;
    EditText etSid;
    TextView tvSub, tvDate, tvStts;
    Spinner spin;

    Calendar c;
    DatePickerDialog dpd;

    String subjects[] = {"Choose Sub","Maths", "Biology", "Chemistry", "Physics", "ICT"};
    String record= "";
    //define array adapter od String type
    ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        buttonBack = findViewById(R.id.btnback);

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Home.class);
                startActivity(i);
            }
        });
        buttonH = findViewById(R.id.btnH);

        buttonH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Home.class);
                startActivity(i);
            }
        });
        buttonA = findViewById(R.id.btnA);

        buttonA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Attendance.class);
                startActivity(i);
            }
        });
        buttonP= findViewById(R.id.btnP);

        buttonP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Payment.class);
                startActivity(i);
            }
        });
        buttonN = findViewById(R.id.btnN);

        buttonN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Notices.class);
                startActivity(i);
            }
        });
        buttonPf = findViewById(R.id.btnpf);

        buttonPf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Profile.class);
                startActivity(i);
            }
        });

        btnDate = findViewById(R.id.BtnpDate);
        btnSubmit = findViewById(R.id.BsubmitP);
        btnOK = findViewById(R.id.BokP);
        etSid = findViewById(R.id.EtSIDP);
        spin = findViewById(R.id.SpinP);
        tvDate = findViewById(R.id.TvDateP);
        tvStts = findViewById(R.id.TvStsP);
        tvSub = findViewById(R.id.TvSubjectP);

        //DatePicker
        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                c = Calendar.getInstance();
                int day = c.get(Calendar.DAY_OF_MONTH);
                int month = c.get(Calendar.MONTH);
                int year = c.get(Calendar.YEAR);

                dpd = new DatePickerDialog(Payment.this, new DatePickerDialog.OnDateSetListener() {
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

        spin.setAdapter(adapter);

        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

    }
    public void displaySubList(View view){

        tvSub.setText(record);
    }

    //SUBMIT METHOD
    public void submit(View view){
        tvStts.setText("");
        try {
            if (TextUtils.isEmpty(etSid.getText().toString()))
                Toast.makeText(getApplicationContext(),"Please enter an ID", Toast.LENGTH_SHORT).show();
            else if (TextUtils.isEmpty(tvSub.getText().toString()))
                Toast.makeText(getApplicationContext(), "Please choose the Subject", Toast.LENGTH_SHORT).show();
            else if (TextUtils.isEmpty(tvDate.getText().toString()))
                Toast.makeText(getApplicationContext(), "Please Select the Date", Toast.LENGTH_SHORT).show();
            else {
                if (tvSub.getText() == "Maths") {
                    DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("MathsTB").child(etSid.getText().toString().trim());
                    readRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (dataSnapshot.hasChildren()) {
                                tvStts.setText(dataSnapshot.child("name").getValue().toString() + "  " + dataSnapshot.child("fee").getValue().toString());

                            } else
                                Toast.makeText(getApplicationContext(), "No Source to Display", Toast.LENGTH_SHORT).show();

                        }


                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
                if (tvSub.getText() == "Physics") {
                    DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("PhysicsTB").child(etSid.getText().toString().trim());
                    readRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (dataSnapshot.hasChildren()) {
                                tvStts.setText(dataSnapshot.child("name").getValue().toString() + "  " + dataSnapshot.child("fee").getValue().toString());

                            } else
                                Toast.makeText(getApplicationContext(), "No Source to Display", Toast.LENGTH_SHORT).show();

                        }


                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
                if (tvSub.getText() == "Chemistry") {
                    DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("ChemistryTB").child(etSid.getText().toString().trim());
                    readRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (dataSnapshot.hasChildren()) {
                                tvStts.setText(dataSnapshot.child("name").getValue().toString() + "  " + dataSnapshot.child("fee").getValue().toString());

                            } else
                                Toast.makeText(getApplicationContext(), "No Source to Display", Toast.LENGTH_SHORT).show();

                        }


                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
                if (tvSub.getText() == "Biology") {
                    DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("BioTB").child(etSid.getText().toString().trim());
                    readRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (dataSnapshot.hasChildren()) {
                                tvStts.setText(dataSnapshot.child("name").getValue().toString() + "  " + dataSnapshot.child("fee").getValue().toString());

                            } else
                                Toast.makeText(getApplicationContext(), "No Source to Display", Toast.LENGTH_SHORT).show();

                        }


                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
                if (tvSub.getText() == "ICT") {
                    DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("ICT_TB").child(etSid.getText().toString().trim());
                    readRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (dataSnapshot.hasChildren()) {
                                tvStts.setText(dataSnapshot.child("name").getValue().toString() + "  " + dataSnapshot.child("fee").getValue().toString());

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
