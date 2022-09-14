package com.example.mygrocerysstore;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class mycartadapter extends RecyclerView.Adapter<mycartadapter.myviewholder> {
    Context context;
    ArrayList<mycartmodel> cartlist;

    public mycartadapter(Context context, ArrayList<mycartmodel> cartlist) {
        this.context = context;
        this.cartlist = cartlist;
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mycartitem,parent,false);
        return new myviewholder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {
        int num = position;
//        holder.cartimage.setImageResource(Integer.parseInt(cartlist.get(num).getOrderimage()));
        holder.cname.setText(cartlist.get(num).getOrdername());
        holder.cprice.setText(cartlist.get(num).getOrderprice());
        holder.cquantity.setText(cartlist.get(num).getOrderquantity());
        Glide.with(holder.cartimage.getContext()).load(cartlist.get(num).getOrderimage()).into(holder.cartimage);

    }


    @Override
    public int getItemCount() {
        return cartlist.size();
    }

    public class myviewholder extends RecyclerView.ViewHolder {
        ImageView cartimage;
        TextView cname,cprice,cquantity;
        public myviewholder(@NonNull View itemView) {
            super(itemView);
            cartimage = itemView.findViewById(R.id.cartimage);
            cname = itemView.findViewById(R.id.orderitemname);
            cprice = itemView.findViewById(R.id.orderitemprice);
            cquantity = itemView.findViewById(R.id.orderitemquantity);
        }
    }
}
