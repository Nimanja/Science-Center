package com.example.sciencecenter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Add_payment_details extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_payment_details);

        textView = findViewById(R.id.text_view_timedate);
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(" EEEE , dd-MMM-yyyy hh:mm:ss a");
        String datetime  = simpleDateFormat.format(calendar.getTime());
        textView.setText(datetime);

    }
}
