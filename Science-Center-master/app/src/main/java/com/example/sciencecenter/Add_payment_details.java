package com.example.sciencecenter;

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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Add_payment_details extends AppCompatActivity {

    EditText etpSID, etpName;
    TextView tvpDate, tvpSubject, tvpFee;
    Button bpDate, bpSubOK, bpFeeOK, bpSave, bpView, bpUpdate, bpDelete;
    Spinner spinSub, spinFee;
    MathsTB mtb;
    BioTB btb;
    PhysicsTB ptb;
    ChemistryTB ctb;
    ICT_TB itb;
    DatabaseReference dbRef;

    String subjects[] = {"Choose Sub","Maths", "Biology", "Chemistry", "Physics", "ICT"};
    String recordSub= "";
    //define array adapter od String type
    ArrayAdapter<String> adapterSub;

    String fees[] = {"Choose Fee","paid Rs.750.00", "paid Rs.1000.00", "paid Rs.1500.00", "Not paid"};
    String recordFee= "";
    //define array adapter od String type
    ArrayAdapter<String> adapterFee;

    Calendar c;
    DatePickerDialog dpd;

    private void clear(){
        etpSID.setText("");
        etpName.setText("");
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_payment_details);

        etpName = findViewById(R.id.EtpName);
        etpSID = findViewById(R.id.EtpSID);
        tvpDate = findViewById(R.id.TvpDate);
        tvpSubject = findViewById(R.id.TvpSub);
        tvpFee = findViewById(R.id.TvpFee);
        bpDate = findViewById(R.id.BtnpDate);
        bpSubOK = findViewById(R.id.BpSub);
        bpFeeOK= findViewById(R.id.BpFee);
        bpSave = findViewById(R.id.BPSave);
        bpView = findViewById(R.id.BpView);
        bpUpdate = findViewById(R.id.BpUpdate);
        bpDelete = findViewById(R.id.BpDelete);
        spinFee = findViewById(R.id.SpinPfee);
        spinSub = findViewById(R.id.SpinPsub);

        mtb = new MathsTB();
        btb = new BioTB();
        ptb = new PhysicsTB();
        ctb = new ChemistryTB();
        itb = new ICT_TB();

        //DatePicker
        bpDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                c = Calendar.getInstance();
                int day = c.get(Calendar.DAY_OF_MONTH);
                int month = c.get(Calendar.MONTH);
                int year = c.get(Calendar.YEAR);

                dpd = new DatePickerDialog(Add_payment_details.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int mYear, int mMonth, int mDay) {
                        tvpDate.setText(mDay + "/" + (mMonth+1) + "/" + mYear);

                    }
                },day,month,year);
                dpd.show();;

            }
        });//end_Of_Date_Picker



        //Spinner for Subjects
        adapterSub = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, subjects);

        spinSub.setAdapter(adapterSub);

        spinSub.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch (position)
                {
                    case 0:
                        recordSub = "Choose Sub";
                        break;

                    case 1:
                        recordSub = "Maths";
                        break;

                    case 2:
                        recordSub = "Biology";
                        break;

                    case 3:
                        recordSub = "Chemistry";
                        break;

                    case 4:
                        recordSub = "Physics";
                        break;

                    case 5:
                        recordSub = "ICT";
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        //Spinner for Fees
        adapterFee = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, fees);

        spinFee.setAdapter(adapterFee);

        spinFee.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch (position)
                {
                    case 0:
                        recordFee = "Choose Fee";
                        break;

                    case 1:
                        recordFee = "paid Rs.750.00";
                        break;

                    case 2:
                        recordFee = "paid Rs.1000.00";
                        break;

                    case 3:
                        recordFee = "paid Rs.1500.00";
                        break;

                    case 4:
                        recordFee = "Not paid";
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
    public void displaySubList(View view) {

        tvpSubject.setText(recordSub);
    }

    public void displayFeeList(View view) {

        tvpFee.setText(recordFee);
    }



    //SAVE OPERATION
    public void save(View view){

        if (tvpSubject.getText()=="Maths")
            dbRef = FirebaseDatabase.getInstance().getReference().child("MathsTB");
        else if (tvpSubject.getText()=="Biology")
            dbRef = FirebaseDatabase.getInstance().getReference().child("BioTB");
        else if (tvpSubject.getText()=="Chemistry")
            dbRef = FirebaseDatabase.getInstance().getReference().child("ChemistryTB");
        else if (tvpSubject.getText()=="Physics")
            dbRef = FirebaseDatabase.getInstance().getReference().child("PhysicsTB");
        else
            dbRef = FirebaseDatabase.getInstance().getReference().child("ICT_TB");




        try {
            if (TextUtils.isEmpty(etpSID.getText().toString()))
                Toast.makeText(getApplicationContext(),"Please enter an ID", Toast.LENGTH_SHORT).show();
            else if (TextUtils.isEmpty(etpName.getText().toString()))
                Toast.makeText(getApplicationContext(), "Please enter the name of Student", Toast.LENGTH_SHORT).show();
            else if (TextUtils.isEmpty(tvpSubject.getText().toString()))
                Toast.makeText(getApplicationContext(), "Please choose the Subject", Toast.LENGTH_SHORT).show();
            else if (TextUtils.isEmpty(tvpDate.getText().toString()))
                Toast.makeText(getApplicationContext(), "Please Select the Date", Toast.LENGTH_SHORT).show();
            else if (TextUtils.isEmpty(tvpFee.getText().toString()))
                Toast.makeText(getApplicationContext(), "Please Select the Payment", Toast.LENGTH_SHORT).show();
            else
            {
                if (tvpSubject.getText()=="Maths"){                                               //MathsTB
                    mtb.setID(Integer.parseInt(etpSID.getText().toString().trim()));
                    mtb.setName(etpName.getText().toString().trim());
                    mtb.setFee(tvpFee.getText().toString().trim());
                    mtb.setSubject(tvpSubject.getText().toString().trim());
                    mtb.setDate(tvpDate.getText().toString().trim());

                    dbRef.push().setValue(mtb);

                    dbRef.child(etpSID.getText().toString().trim()).setValue(mtb);

                    Toast.makeText(getApplicationContext(), "Data Saved Successfully", Toast.LENGTH_SHORT).show();
                    clear();


                }
                if (tvpSubject.getText()=="Biology"){                                            //BiologyTB
                    btb.setID(Integer.parseInt(etpSID.getText().toString().trim()));
                    btb.setName(etpName.getText().toString().trim());
                    btb.setFee(tvpFee.getText().toString().trim());
                    btb.setSubject(tvpSubject.getText().toString().trim());
                    btb.setDate(tvpDate.getText().toString().trim());

                    dbRef.push().setValue(btb);

                    dbRef.child(etpSID.getText().toString().trim()).setValue(btb);

                    Toast.makeText(getApplicationContext(), "Data Saved Successfully", Toast.LENGTH_SHORT).show();
                    clear();


                }
                if (tvpSubject.getText()=="Chemistry"){                                         //ChemistryTB
                    ctb.setID(Integer.parseInt(etpSID.getText().toString().trim()));
                    ctb.setName(etpName.getText().toString().trim());
                    ctb.setFee(tvpFee.getText().toString().trim());
                    ctb.setSubject(tvpSubject.getText().toString().trim());
                    ctb.setDate(tvpDate.getText().toString().trim());

                    dbRef.push().setValue(ctb);

                    dbRef.child(etpSID.getText().toString().trim()).setValue(ctb);

                    Toast.makeText(getApplicationContext(), "Data Saved Successfully", Toast.LENGTH_SHORT).show();
                    clear();


                }
                if (tvpSubject.getText()=="Physics"){                                         //PhysicsTB
                    ptb.setID(Integer.parseInt(etpSID.getText().toString().trim()));
                    ptb.setName(etpName.getText().toString().trim());
                    ptb.setFee(tvpFee.getText().toString().trim());
                    ptb.setSubject(tvpSubject.getText().toString().trim());
                    ptb.setDate(tvpDate.getText().toString().trim());

                    dbRef.push().setValue(ptb);

                    dbRef.child(etpSID.getText().toString().trim()).setValue(ptb);

                    Toast.makeText(getApplicationContext(), "Data Saved Successfully", Toast.LENGTH_SHORT).show();
                    clear();


                }
                if (tvpSubject.getText()=="ICT"){                                            //ICT_TB
                    itb.setID(Integer.parseInt(etpSID.getText().toString().trim()));
                    itb.setName(etpName.getText().toString().trim());
                    itb.setFee(tvpFee.getText().toString().trim());
                    itb.setSubject(tvpSubject.getText().toString().trim());
                    itb.setDate(tvpDate.getText().toString().trim());

                    dbRef.push().setValue(itb);

                    dbRef.child(etpSID.getText().toString().trim()).setValue(itb);

                    Toast.makeText(getApplicationContext(), "Data Saved Successfully", Toast.LENGTH_SHORT).show();
                    clear();


                }
            }

        }
        catch (NumberFormatException e){
            Toast.makeText(getApplicationContext(), "Invalid ID", Toast.LENGTH_SHORT).show();
        }


    }


}
