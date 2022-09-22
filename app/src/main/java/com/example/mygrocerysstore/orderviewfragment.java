package com.example.mygrocerysstore;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class orderviewfragment extends Fragment {
    TextView ordername,orderprice,quantity;
    ImageView orderimageview,add,Subtract;
    Bundle bundle;
    String txtname,txtprice,urll;
    Button button;
    int  num= 1;
    String qnum;
    FirebaseUser Currentuser;
    DatabaseReference databaseReference;
    FirebaseAuth mAuth;
    FirebaseDatabase firebaseDatabase;
    int maxid =0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_orderviewfragment, container, false);
        ordername = view.findViewById(R.id.orderitemname);
        orderprice = view.findViewById(R.id.orderitemprice);
        orderimageview = view.findViewById(R.id.orderimageview);
        add = view.findViewById(R.id.add);
        Subtract = view.findViewById(R.id.minus);
        quantity = view.findViewById(R.id.quantitytxt);
        button = view.findViewById(R.id.addtocart);

        bundle = this.getArguments();
        if(bundle!=null){
            txtname = bundle.getString("keyname");
            txtprice = bundle.getString("keyprice");
            urll = bundle.getString("url");
        }
        Toast.makeText(getContext(), ""+txtname, Toast.LENGTH_SHORT).show();
        ordername.setText(txtname);
        orderprice.setText(txtprice);
        Glide.with(getContext()).load(urll).into(orderimageview);


        // click listhner work
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(num<=5){
                    num = num+1;
                    quantity.setText(num+"");
                }
                else{
                    Toast.makeText(getContext(), "Maximum six quantity order in one time", Toast.LENGTH_SHORT).show();
                }
            }
        });
        Subtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(num>=2){
                    num = num-1;
                    quantity.setText(num+"");
                }
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mycartmodel mycartmodel = new mycartmodel();
                mycartmodel.setOrdername(txtname);
                mycartmodel.setOrderprice(txtprice);
                mycartmodel.setOrderquantity(num+"");
                mycartmodel.setOrderimage(urll);
                Currentuser = mAuth.getInstance().getCurrentUser();
                String id = Currentuser.getUid();
                firebaseDatabase = FirebaseDatabase.getInstance();
                firebaseDatabase.getReference("cartdetail").child(id).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        long i = snapshot.getChildrenCount();
                        firebaseDatabase.getReference("cartdetail").child(id).child(String.valueOf(i+1)).setValue(mycartmodel);
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container,new carttfragment()).commit();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
//                databaseReference.child().setValue(mycartmodel);

            }
        });


        return view;
    }
}