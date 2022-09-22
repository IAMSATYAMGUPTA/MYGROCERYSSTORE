package com.example.mygrocerysstore;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
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
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context).setTitle("Delete note")
                        .setMessage("Are you sure want to delete ? ")
                        .setIcon(R.drawable.ic_baseline_delete_24)
                        .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(context, "Note deleted", Toast.LENGTH_SHORT).show();
                                cartlist.remove(num);
                                notifyItemRemoved(num);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                builder.show();
                return true;
            }
        });

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
