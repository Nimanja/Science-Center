package com.example.sciencecenter;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;

import java.util.Calendar;

public class Attendance_Manage extends AppCompatActivity {

    Spinner spin;
    TextView tvdate1;
    EditText etID, etName;
    RadioButton rbAbsent, rbPresent;
    Button btnSave, btnView, btnUpdate, btnDelete, btnCal;
    MathsTB mtb;
    BioTB btb;
    PhysicsTB ptb;
    ChemistryTB ctb;
    ICT_TB itb;
    DatabaseReference dbRef;

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
        etID = findViewById(R.id.EtStID1);
        etName = findViewById(R.id.StdName);
        rbPresent = findViewById(R.id.RbPr);
        rbAbsent = findViewById(R.id.RbAb);
        btnCal = findViewById(R.id.BtnCal);
        btnSave = findViewById(R.id.BtnSave);
        btnView = findViewById(R.id.BtnView);
        btnUpdate = findViewById(R.id.BtnUpdate);
        btnDelete = findViewById(R.id.BtnDelete);

        mtb = new MathsTB();
        btb = new BioTB();
        ptb = new PhysicsTB();
        ctb = new ChemistryTB();
        itb = new ICT_TB();



    }
}
