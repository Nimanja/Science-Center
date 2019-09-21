package com.example.sciencecenter;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class AdminNotices extends AppCompatActivity {

    private TextView msetDate;
    private DatePickerDialog.OnDateSetListener mDatSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_notices);

        msetDate = (TextView) findViewById(R.id.setDate);

        msetDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog dialog = new DatePickerDialog(
                    AdminNotices.this,
                    android.R.style.Theme_Holo_Light_Dialog_MinWidth,mDatSetListener,
                    year,month,day);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();
            }
        });
        mDatSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                Log.d(String.valueOf(this),"onDateSet : mm/dd/yyyy: " + month + "/" + dayOfMonth + "/" +year);

                String date = month + "/" + dayOfMonth + "/" + year;
                msetDate.setText(date);
            }
        };
    }
}
