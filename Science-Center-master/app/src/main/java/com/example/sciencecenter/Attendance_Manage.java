package com.example.sciencecenter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.Date;

public class Attendance_Manage extends AppCompatActivity {

    Spinner spin;
    TextView tvdate1, tvsub, tvatt;
    EditText etID, etName;
    RadioButton rbAbsent, rbPresent;
    Button btnSave, btnView, btnUpdate, btnDelete, btnCal, btnOK;
    MathsTB mtb;
    BioTB btb;
    PhysicsTB ptb;
    ChemistryTB ctb;
    ICT_TB itb;
    DatabaseReference dbRef;

    String subjects[] = {"Choose Sub","Maths", "Biology", "Chemistry", "Physics", "ICT"};
    String record= "";
    //define array adapter od String type
    ArrayAdapter <String> adapter;


    Calendar c;
    DatePickerDialog dpd;

    private void clear(){
        etID.setText("");
        etName.setText("");
        rbAbsent.setSelected(false);
        rbPresent.setSelected(false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance__manage);

        spin = findViewById(R.id.Spin1);
        tvdate1 = findViewById(R.id.Tvdate1);
        tvatt = findViewById(R.id.Tvatt);
        tvsub = findViewById(R.id.TvSub);
        etID = findViewById(R.id.EtStID1);
        etName = findViewById(R.id.StdName);
        rbPresent = findViewById(R.id.RbPr);
        rbAbsent = findViewById(R.id.RbAb);
        btnCal = findViewById(R.id.BtnCal);
        btnOK = findViewById(R.id.BtnOK);
        btnSave = findViewById(R.id.BtnSave);
        btnView = findViewById(R.id.BtnView);
        btnUpdate = findViewById(R.id.BtnUpdate);
        btnDelete = findViewById(R.id.BtnDelete);

        mtb = new MathsTB();
        btb = new BioTB();
        ptb = new PhysicsTB();
        ctb = new ChemistryTB();
        itb = new ICT_TB();




        //DatePicker
        btnCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                c = Calendar.getInstance();
                int day = c.get(Calendar.DAY_OF_MONTH);
                int month = c.get(Calendar.MONTH);
                int year = c.get(Calendar.YEAR);

                dpd = new DatePickerDialog(Attendance_Manage.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int mYear, int mMonth, int mDay) {
                        tvdate1.setText(mDay + "/" + (mMonth+1) + "/" + mYear);

                    }
                },day,month,year);
                dpd.show();;

            }
        });//end_Of_Date_Picker


        //radio_Button
       // public void onRadioButtonClicked(){
            /*// Is the button now checked?
            boolean checked = ((RadioButton) view).isChecked();

            // Check which radio button was clicked
            switch(view.getId()) {
                case R.id.RbAb:
                    if (checked)
                        tvatt.setText("Absent");
                        break;
                case R.id.RbPr:
                    if (checked)
                        tvatt.setText("Present");
                        break;
            }*/
       // }


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
        });
    }
    public void displaySubList(View view){

        tvsub.setText(record);
    }//end_OF_Spinner


    //SAVE OPERATION
    public void save(View view){

        if (tvsub.getText()=="Maths")
        dbRef = FirebaseDatabase.getInstance().getReference().child("MathsTB");
            else if (tvsub.getText()=="Biology")
            dbRef = FirebaseDatabase.getInstance().getReference().child("BioTB");
                else if (tvsub.getText()=="Chemistry")
                dbRef = FirebaseDatabase.getInstance().getReference().child("ChemistryTB");
                    else if (tvsub.getText()=="Physics")
                    dbRef = FirebaseDatabase.getInstance().getReference().child("PhysicsTB");
                        else
                        dbRef = FirebaseDatabase.getInstance().getReference().child("ICT_TB");




        try {
           if (TextUtils.isEmpty(etID.getText().toString()))
               Toast.makeText(getApplicationContext(),"Please enter an ID", Toast.LENGTH_SHORT).show();
           else if (TextUtils.isEmpty(etName.getText().toString()))
               Toast.makeText(getApplicationContext(), "Please enter the name of Student", Toast.LENGTH_SHORT).show();
           else if (TextUtils.isEmpty(tvsub.getText().toString()))
               Toast.makeText(getApplicationContext(), "Please choose the Subject", Toast.LENGTH_SHORT).show();
           else if (TextUtils.isEmpty(tvdate1.getText().toString()))
               Toast.makeText(getApplicationContext(), "Please Select the Date", Toast.LENGTH_SHORT).show();
           else if (TextUtils.isEmpty(tvatt.getText().toString()))
               Toast.makeText(getApplicationContext(), "Please Select the Attendance", Toast.LENGTH_SHORT).show();
           else
           {
               if (tvsub.getText()=="Maths"){                                               //MathsTB
                   mtb.setID(Integer.parseInt(etID.getText().toString().trim()));
                   mtb.setName(etName.getText().toString().trim());
                   mtb.setAttendance(tvatt.getText().toString().trim());
                   mtb.setSubject(tvsub.getText().toString().trim());
                   mtb.setDate(tvdate1.getText().toString().trim());

                   dbRef.push().setValue(mtb);

                   dbRef.child(etID.getText().toString().trim()).setValue(mtb);

                   Toast.makeText(getApplicationContext(), "Data Saved Successfully", Toast.LENGTH_SHORT).show();
                   clear();


               }
               if (tvsub.getText()=="Biology"){                                            //BiologyTB
                   btb.setID(Integer.parseInt(etID.getText().toString().trim()));
                   btb.setName(etName.getText().toString().trim());
                   btb.setAttendance(tvatt.getText().toString().trim());
                   btb.setSubject(tvsub.getText().toString().trim());
                   btb.setDate(tvdate1.getText().toString().trim());

                   dbRef.push().setValue(btb);

                   dbRef.child(etID.getText().toString().trim()).setValue(btb);

                   Toast.makeText(getApplicationContext(), "Data Saved Successfully", Toast.LENGTH_SHORT).show();
                   clear();


               }
               if (tvsub.getText()=="Chemistry"){                                         //ChemistryTB
                   ctb.setID(Integer.parseInt(etID.getText().toString().trim()));
                   ctb.setName(etName.getText().toString().trim());
                   ctb.setAttendance(tvatt.getText().toString().trim());
                   ctb.setSubject(tvsub.getText().toString().trim());
                   ctb.setDate(tvdate1.getText().toString().trim());

                   dbRef.push().setValue(ctb);

                   dbRef.child(etID.getText().toString().trim()).setValue(ctb);

                   Toast.makeText(getApplicationContext(), "Data Saved Successfully", Toast.LENGTH_SHORT).show();
                   clear();


               }
               if (tvsub.getText()=="Physics"){                                         //PhysicsTB
                   ptb.setID(Integer.parseInt(etID.getText().toString().trim()));
                   ptb.setName(etName.getText().toString().trim());
                   ptb.setAttendance(tvatt.getText().toString().trim());
                   ptb.setSubject(tvsub.getText().toString().trim());
                   ptb.setDate(tvdate1.getText().toString().trim());

                   dbRef.push().setValue(ptb);

                   dbRef.child(etID.getText().toString().trim()).setValue(ptb);

                   Toast.makeText(getApplicationContext(), "Data Saved Successfully", Toast.LENGTH_SHORT).show();
                   clear();


               }
               if (tvsub.getText()=="ICT"){                                            //ICT_TB
                   itb.setID(Integer.parseInt(etID.getText().toString().trim()));
                   itb.setName(etName.getText().toString().trim());
                   itb.setAttendance(tvatt.getText().toString().trim());
                   itb.setSubject(tvsub.getText().toString().trim());
                   itb.setDate(tvdate1.getText().toString().trim());

                   dbRef.push().setValue(itb);

                   dbRef.child(etID.getText().toString().trim()).setValue(itb);

                   Toast.makeText(getApplicationContext(), "Data Saved Successfully", Toast.LENGTH_SHORT).show();
                   clear();


               }
           }

        }
        catch (NumberFormatException e){
            Toast.makeText(getApplicationContext(), "Invalid ID", Toast.LENGTH_SHORT).show();
        }






    }

    //VIEW OPERATION
    public void view(View view){

        if (tvsub.getText()=="Maths") {
            DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("MathsTB").child(etID.getText().toString().trim());
            readRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.hasChildren()) {
                        etName.setText(dataSnapshot.child("name").getValue().toString());

                    } else
                        Toast.makeText(getApplicationContext(), "No Source to Display", Toast.LENGTH_SHORT).show();

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
        if (tvsub.getText()=="Physics") {
            DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("PhysicsTB").child(etID.getText().toString().trim());
            readRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.hasChildren()) {
                        etName.setText(dataSnapshot.child("name").getValue().toString());

                    } else
                        Toast.makeText(getApplicationContext(), "No Source to Display", Toast.LENGTH_SHORT).show();

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
        if (tvsub.getText()=="Chemistry") {
            DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("ChemistryTB").child(etID.getText().toString().trim());
            readRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.hasChildren()) {
                        etName.setText(dataSnapshot.child("name").getValue().toString());

                    } else
                        Toast.makeText(getApplicationContext(), "No Source to Display", Toast.LENGTH_SHORT).show();

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
        if (tvsub.getText()=="Biology") {
            DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("BioTB").child(etID.getText().toString().trim());
            readRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.hasChildren()) {
                        etName.setText(dataSnapshot.child("name").getValue().toString());

                    } else
                        Toast.makeText(getApplicationContext(), "No Source to Display", Toast.LENGTH_SHORT).show();

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
        if (tvsub.getText()=="ICT") {
            DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("ICT_TB").child(etID.getText().toString().trim());
            readRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.hasChildren()) {
                        etName.setText(dataSnapshot.child("name").getValue().toString());

                    } else
                        Toast.makeText(getApplicationContext(), "No Source to Display", Toast.LENGTH_SHORT).show();

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }

    }

    //UPDATE OPERATION
    public void update(View view){

        if (tvsub.getText()=="Maths") {
            DatabaseReference upRef = FirebaseDatabase.getInstance().getReference().child("MathsTB");
            upRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.hasChild("etID.getText().toString().trim()")) {
                        try {
                            mtb.setID(Integer.parseInt(etID.getText().toString().trim()));
                            mtb.setName(etName.getText().toString().trim());
                            mtb.setAttendance(tvatt.getText().toString().trim());
                            mtb.setSubject(tvsub.getText().toString().trim());
                            mtb.setDate(tvdate1.getText().toString().trim());

                            dbRef = FirebaseDatabase.getInstance().getReference().child("MathsTB").child(etID.getText().toString().trim());
                            dbRef.setValue(mtb);
                            clear();

                            Toast.makeText(getApplicationContext(), "Data Updated Successfully", Toast.LENGTH_SHORT).show();
                        } catch (NumberFormatException e) {
                            Toast.makeText(getApplicationContext(), "Invalid ID Number", Toast.LENGTH_SHORT).show();
                        }

                    } else
                        Toast.makeText(getApplicationContext(), "No Source to Update", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }

        if (tvsub.getText()=="Biology") {
            DatabaseReference upRef = FirebaseDatabase.getInstance().getReference().child("BioTB");
            upRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.hasChild("etID.getText().toString().trim()")) {
                        try {
                            mtb.setID(Integer.parseInt(etID.getText().toString().trim()));
                            mtb.setName(etName.getText().toString().trim());
                            mtb.setAttendance(tvatt.getText().toString().trim());
                            mtb.setSubject(tvsub.getText().toString().trim());
                            mtb.setDate(tvdate1.getText().toString().trim());

                            dbRef = FirebaseDatabase.getInstance().getReference().child("BioTB").child(etID.getText().toString().trim());
                            dbRef.setValue(btb);
                            clear();

                            Toast.makeText(getApplicationContext(), "Data Updated Successfully", Toast.LENGTH_SHORT).show();
                        } catch (NumberFormatException e) {
                            Toast.makeText(getApplicationContext(), "Invalid ID Number", Toast.LENGTH_SHORT).show();
                        }

                    } else
                        Toast.makeText(getApplicationContext(), "No Source to Update", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }

        if (tvsub.getText()=="Physics") {
            DatabaseReference upRef = FirebaseDatabase.getInstance().getReference().child("PhysicsTB");
            upRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.hasChild("etID.getText().toString().trim()")) {
                        try {
                            mtb.setID(Integer.parseInt(etID.getText().toString().trim()));
                            mtb.setName(etName.getText().toString().trim());
                            mtb.setAttendance(tvatt.getText().toString().trim());
                            mtb.setSubject(tvsub.getText().toString().trim());
                            mtb.setDate(tvdate1.getText().toString().trim());

                            dbRef = FirebaseDatabase.getInstance().getReference().child("PhysicsTB").child(etID.getText().toString().trim());
                            dbRef.setValue(ptb);
                            clear();

                            Toast.makeText(getApplicationContext(), "Data Updated Successfully", Toast.LENGTH_SHORT).show();
                        } catch (NumberFormatException e) {
                            Toast.makeText(getApplicationContext(), "Invalid ID Number", Toast.LENGTH_SHORT).show();
                        }

                    } else
                        Toast.makeText(getApplicationContext(), "No Source to Update", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }

        if (tvsub.getText()=="Chemistry") {
            DatabaseReference upRef = FirebaseDatabase.getInstance().getReference().child("ChemistryTB");
            upRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.hasChild("etID.getText().toString().trim()")) {
                        try {
                            mtb.setID(Integer.parseInt(etID.getText().toString().trim()));
                            mtb.setName(etName.getText().toString().trim());
                            mtb.setAttendance(tvatt.getText().toString().trim());
                            mtb.setSubject(tvsub.getText().toString().trim());
                            mtb.setDate(tvdate1.getText().toString().trim());

                            dbRef = FirebaseDatabase.getInstance().getReference().child("ChemistryTB").child(etID.getText().toString().trim());
                            dbRef.setValue(ctb);
                            clear();

                            Toast.makeText(getApplicationContext(), "Data Updated Successfully", Toast.LENGTH_SHORT).show();
                        } catch (NumberFormatException e) {
                            Toast.makeText(getApplicationContext(), "Invalid ID Number", Toast.LENGTH_SHORT).show();
                        }

                    } else
                        Toast.makeText(getApplicationContext(), "No Source to Update", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
        if (tvsub.getText()=="ICT") {
            DatabaseReference upRef = FirebaseDatabase.getInstance().getReference().child("ICT_TB");
            upRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.hasChild("etID.getText().toString().trim()")) {
                        try {
                            mtb.setID(Integer.parseInt(etID.getText().toString().trim()));
                            mtb.setName(etName.getText().toString().trim());
                            mtb.setAttendance(tvatt.getText().toString().trim());
                            mtb.setSubject(tvsub.getText().toString().trim());
                            mtb.setDate(tvdate1.getText().toString().trim());

                            dbRef = FirebaseDatabase.getInstance().getReference().child("ICT_TB").child(etID.getText().toString().trim());
                            dbRef.setValue(itb);
                            clear();

                            Toast.makeText(getApplicationContext(), "Data Updated Successfully", Toast.LENGTH_SHORT).show();
                        } catch (NumberFormatException e) {
                            Toast.makeText(getApplicationContext(), "Invalid ID Number", Toast.LENGTH_SHORT).show();
                        }

                    } else
                        Toast.makeText(getApplicationContext(), "No Source to Update", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }

    }

    //DELETE OPERATION
    public void delete(View view){

        if (tvsub.getText()=="Maths") {
            DatabaseReference delRef = FirebaseDatabase.getInstance().getReference().child("MathsTB");
            delRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.hasChild("etID.getText().toString().trim()")) {
                        dbRef = FirebaseDatabase.getInstance().getReference().child("MathsTB").child("etID.getText().toString().trim()");
                        dbRef.removeValue();
                        clear();
                        Toast.makeText(getApplicationContext(), "Data Deleted Successfully", Toast.LENGTH_SHORT).show();
                    } else
                        Toast.makeText(getApplicationContext(), "No Source to Delete", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
        if (tvsub.getText()=="Biology") {
            DatabaseReference delRef = FirebaseDatabase.getInstance().getReference().child("BioTB");
            delRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.hasChild("etID.getText().toString().trim()")) {
                        dbRef = FirebaseDatabase.getInstance().getReference().child("BioTB").child("etID.getText().toString().trim()");
                        dbRef.removeValue();
                        clear();
                        Toast.makeText(getApplicationContext(), "Data Deleted Successfully", Toast.LENGTH_SHORT).show();
                    } else
                        Toast.makeText(getApplicationContext(), "No Source to Delete", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
        if (tvsub.getText()=="Physics") {
            DatabaseReference delRef = FirebaseDatabase.getInstance().getReference().child("PhysicsTB");
            delRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.hasChild("etID.getText().toString().trim()")) {
                        dbRef = FirebaseDatabase.getInstance().getReference().child("PhysicsTB").child("etID.getText().toString().trim()");
                        dbRef.removeValue();
                        clear();
                        Toast.makeText(getApplicationContext(), "Data Deleted Successfully", Toast.LENGTH_SHORT).show();
                    } else
                        Toast.makeText(getApplicationContext(), "No Source to Delete", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }

        if (tvsub.getText()=="Chemistry") {
            DatabaseReference delRef = FirebaseDatabase.getInstance().getReference().child("ChemistryTB");
            delRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.hasChild("etID.getText().toString().trim()")) {
                        dbRef = FirebaseDatabase.getInstance().getReference().child("ChemistryTB").child("etID.getText().toString().trim()");
                        dbRef.removeValue();
                        clear();
                        Toast.makeText(getApplicationContext(), "Data Deleted Successfully", Toast.LENGTH_SHORT).show();
                    } else
                        Toast.makeText(getApplicationContext(), "No Source to Delete", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
        if (tvsub.getText()=="ICT") {
            DatabaseReference delRef = FirebaseDatabase.getInstance().getReference().child("ICT_TB");
            delRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.hasChild("etID.getText().toString().trim()")) {
                        dbRef = FirebaseDatabase.getInstance().getReference().child("ICT_TB").child("etID.getText().toString().trim()");
                        dbRef.removeValue();
                        clear();
                        Toast.makeText(getApplicationContext(), "Data Deleted Successfully", Toast.LENGTH_SHORT).show();
                    } else
                        Toast.makeText(getApplicationContext(), "No Source to Delete", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }

    }//end of Delete

}
