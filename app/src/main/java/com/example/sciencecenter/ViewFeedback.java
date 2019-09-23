package com.example.sciencecenter;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class ViewFeedback extends AppCompatActivity {

    private static final String TAG = "ViewFeedback";

    private DatabaseReference reference;
    private StorageReference mStorageRef;
    private Context mContext = ViewFeedback.this;
    private RecyclerView mName, mPhone, mCommnt;

    private ArrayList<JavaView> JavaViewList;
    private ViewRecyclerAdapter recyclerAdapater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_feedback);

        Log.d(TAG, "onCreate Stage");

        mName = (RecyclerView) findViewById(R.id.recyclerView);
        mPhone = (RecyclerView) findViewById(R.id.recyclerView);
        mCommnt = (RecyclerView) findViewById(R.id.recyclerView);

        reference = FirebaseDatabase.getInstance().getReference();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mName.setLayoutManager(layoutManager);
        mPhone.setLayoutManager(layoutManager);
        mCommnt.setLayoutManager(layoutManager);

//      mName.setHasFixedSize(true);

        reference = FirebaseDatabase.getInstance().getReference();
//      mStorageRef = FirebaseStorage.getInstance().getReference();

        JavaViewList = new ArrayList<>();

        init();
        
    }

    private void init() {
        clearAll();

        Query query = reference.child("JavaView");

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){

                    JavaView javaView = new JavaView();

                    javaView.setmName(snapshot.child("Name").getValue().toString());
                    javaView.setmPhone(snapshot.child("PhoneNumber").getValue().toString());
                    javaView.setmCommnt(snapshot.child("Comment").getValue().toString());

                    JavaViewList.add(javaView);
                }

                recyclerAdapater = new ViewRecyclerAdapter(mContext,JavaViewList);
                mName.setAdapter(recyclerAdapater);
                recyclerAdapater.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void clearAll() {

        if (JavaViewList != null){

            JavaViewList.clear();

            if (recyclerAdapater != null){
                recyclerAdapater.notifyDataSetChanged();
            }
        }
        JavaViewList = new ArrayList<>();
    }
}