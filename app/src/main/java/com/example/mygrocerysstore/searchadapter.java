package com.example.mygrocerysstore;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.ArrayList;

public class searchadapter extends FirebaseRecyclerAdapter<searchmodel,searchadapter.myviewholder> {


    public searchadapter(@NonNull FirebaseRecyclerOptions<searchmodel> options) {
        super(options);
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_item,parent,false);
        return new myviewholder(view);
    }


    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull searchmodel model) {
        holder.sename.setText(model.getSname());
        holder.seprice.setText(model.getSprice());
        Glide.with(holder.seimage.getContext()).load(model.getSimage()).into(holder.seimage);
    }




    public class myviewholder extends RecyclerView.ViewHolder {
        ImageView seimage;
        TextView sename,seprice;
        public myviewholder(@NonNull View itemView) {
            super(itemView);
            seimage = itemView.findViewById(R.id.seimage);
            sename = itemView.findViewById(R.id.sename);
            seprice = itemView.findViewById(R.id.seprice);
        }
    }
}
