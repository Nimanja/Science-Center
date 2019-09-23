package com.example.sciencecenter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ViewRecyclerAdapter extends RecyclerView.Adapter<ViewRecyclerAdapter.ViewHolder> {

    private static final String TAG = "ViewRecyclerAdapter";

    private Context mContext;
    private ArrayList<JavaView> JavaViewList;


    public ViewRecyclerAdapter (Context context, ArrayList<JavaView> JavaViewList){
        this.mContext = context;
        this.JavaViewList = JavaViewList;

    }

    @NonNull
    @Override
    public ViewRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_recyclerview_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.UserName.setText(JavaViewList.get(position).getmName());
        holder.PhoneNumber.setText(JavaViewList.get(position).getmPhone());
        holder.commentView.setText(JavaViewList.get(position).getmCommnt());

    }

    @Override
    public int getItemCount() {
        return JavaViewList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView UserName,PhoneNumber,commentView;


    public ViewHolder(View itemView){

        super(itemView);

        UserName = (TextView) itemView.findViewById(R.id.Name);
        PhoneNumber = (TextView) itemView.findViewById(R.id.PhoneNumber);
        commentView = (TextView) itemView.findViewById(R.id.comment);

    }
    }
}