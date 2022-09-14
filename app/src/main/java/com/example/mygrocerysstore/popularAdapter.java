package com.example.mygrocerysstore;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;

public class popularAdapter extends RecyclerView.Adapter<popularAdapter.myviewholder> {
    ArrayList<popularmodel> popularlist;
    Context context;

    public popularAdapter(ArrayList<popularmodel> popularlist, Context context) {
        this.popularlist = popularlist;
        this.context = context;
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.popula_item,parent,false);
        return  new myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {
        holder.popularname.setText(popularlist.get(position).getName());
        holder.populardescription.setText(popularlist.get(position).getDescription());
        holder.populardiscount.setText(popularlist.get(position).getDiscount());
        Glide.with(holder.popularimage.getContext()).load(popularlist.get(position).getTopimages()).into(holder.popularimage);
    }

    @Override
    public int getItemCount() {
        return popularlist.size();
    }

    public class myviewholder extends RecyclerView.ViewHolder {
        TextView popularname,populardescription,populardiscount;
        RoundedImageView popularimage;

        public myviewholder(@NonNull View itemView) {
            super(itemView);
            popularname = itemView.findViewById(R.id.popularname);
            populardescription = itemView.findViewById(R.id.populardescription);
            populardiscount = itemView.findViewById(R.id.populardiscount);
            popularimage = itemView.findViewById(R.id.popularimage);

        }
    }
}
