package com.example.mygrocerysstore;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class fruitadapter extends RecyclerView.Adapter<fruitadapter.myviewholder> {
    ArrayList<fruitmodel> flist;
    Context context;

    public fruitadapter(ArrayList<fruitmodel> flist, Context context) {
        this.flist = flist;
        this.context = context;
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fruititem,parent,false);
        return new myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {
        int num =position;
        holder.fname.setText(flist.get(position).getFname());
        holder.fprice.setText(flist.get(position).getFprice());
        Glide.with(holder.fimage.getContext()).load(flist.get(position).getFimage()).into(holder.fimage);
        holder.fimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("keyname",flist.get(num).getFname());
                bundle.putString("url",flist.get(num).getFimage());
                bundle.putString("keyprice",flist.get(num).getFprice());
                orderviewfragment orderviewfragment = new orderviewfragment();
                orderviewfragment.setArguments(bundle);
                AppCompatActivity activity = (AppCompatActivity)v.getContext();
                FragmentManager fragmentManager =  activity.getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container,orderviewfragment);
                fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return flist.size();
    }

    public class myviewholder extends  RecyclerView.ViewHolder {
        ImageView fimage;
        TextView fname,fprice;

        public myviewholder(@NonNull View itemView) {
            super(itemView);
            fimage = itemView.findViewById(R.id.fruitcategoryimage);
            fprice = itemView.findViewById(R.id.fruitprice);
            fname = itemView.findViewById(R.id.fruitname);
        }
    }
}
