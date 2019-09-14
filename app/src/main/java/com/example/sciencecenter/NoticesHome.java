package com.example.sciencecenter;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class NoticesHome extends Fragment {

    View v;
    Button classBtn;
    private Button FeedbackBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_notices, container, false);

        classBtn = v.findViewById(R.id.btnclz);
        classBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.defaultFragment, new Class()).commit();
            }
        });
/*
        classBtn = v.findViewById(R.id.btnFeedback);
        classBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.defaultFragment, new FeedbackFragment()).commit();
            }
        });
*/
        FeedbackBtn = v.findViewById(R.id.btnFeedback);
        FeedbackBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),NoticeFeedback.class);
                startActivity(intent);
            }
        });

        return v;
    }

}
