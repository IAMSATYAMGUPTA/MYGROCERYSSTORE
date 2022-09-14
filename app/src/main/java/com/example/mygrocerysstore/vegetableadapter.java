package com.example.mygrocerysstore;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;

public class vegetableadapter extends RecyclerView.Adapter<vegetableadapter.myviewholder> {
    ArrayList<vegetablemodel> vegetablelist;
    Context context;

    public vegetableadapter(ArrayList<vegetablemodel> vegetablelist, Context context) {
        this.vegetablelist = vegetablelist;
        this.context = context;
    }


    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.vegetable_item,parent,false);
        return new myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {
        int num =position;
        holder.vegename.setText(vegetablelist.get(position).getVegename());
        holder.vegeprice.setText(vegetablelist.get(position).getVegeprice());
        Glide.with(holder.vegeimage.getContext()).load(vegetablelist.get(position).getVegeimage()).into(holder.vegeimage);
        holder.vegeimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("keyname",vegetablelist.get(num).getVegename());
                bundle.putString("url",vegetablelist.get(num).getVegeimage());
                bundle.putString("keyprice",vegetablelist.get(num).getVegeprice());
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
        return vegetablelist.size();
    }

    public class myviewholder extends RecyclerView.ViewHolder {
        TextView vegename,vegeprice;
        RoundedImageView vegeimage;

        public myviewholder(@NonNull View itemView) {
            super(itemView);
            vegename = itemView.findViewById(R.id.vegename);
            vegeimage = itemView.findViewById(R.id.vegetablecategoryimg);
            vegeprice = itemView.findViewById(R.id.vegeprice);
        }
    }
}
