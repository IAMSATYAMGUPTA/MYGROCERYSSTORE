package com.example.mygrocerysstore;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;

public class allvegetableadapter extends RecyclerView.Adapter<allvegetableadapter.myviewholder> {
    ArrayList<allvegetablemodel> valist;
    Context context;

    public allvegetableadapter(ArrayList<allvegetablemodel> valist, Context context) {
        this.valist = valist;
        this.context = context;
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewallvegetable,parent,false);
        return  new myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {
        int num = position;
        holder.allvegename.setText(valist.get(num).getVaname());
        holder.allvegeprice.setText(valist.get(num).getVaprice());
        Glide.with(holder.allvegeimg.getContext()).load(valist.get(num).getVaimage()).into(holder.allvegeimg);
        holder.allvegeimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("keyname",valist.get(num).getVaname());
                bundle.putString("url",valist.get(num).getVaimage());
                bundle.putString("keyprice",valist.get(num).getVaprice());
                orderviewfragment orderviewfragment = new orderviewfragment();
                orderviewfragment.setArguments(bundle);
                AppCompatActivity activity = (AppCompatActivity)v.getContext();
                FragmentManager fragmentManager =  activity.getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container,orderviewfragment);
                fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

//                activity.getSupportFragmentManager().beginTransaction().replace(R.id.container,new orderviewfragment()).commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return valist.size();
    }

    public class myviewholder extends RecyclerView.ViewHolder {
        RoundedImageView allvegeimg;
        TextView allvegename,allvegeprice;
        public myviewholder(@NonNull View itemView) {
            super(itemView);
            allvegeimg = itemView.findViewById(R.id.vallimg);
            allvegename = itemView.findViewById(R.id.vallname);
            allvegeprice = itemView.findViewById(R.id.vallprice);

        }
    }
}