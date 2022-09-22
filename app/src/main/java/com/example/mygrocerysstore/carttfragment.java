package com.example.mygrocerysstore;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class carttfragment extends Fragment implements PaymentResultListener {
    RecyclerView cartrecycle;
    ArrayList<mycartmodel> cartlist = new ArrayList<>();
    mycartadapter mycartadapter;
    FirebaseUser Currentuser;
    DatabaseReference databaseReference;
    FirebaseAuth mAuth;
    Button orderplacedbtn;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_carttfragment, container, false);
        cartrecycle = view.findViewById(R.id.mycartrecycle);
        mycartadapter = new mycartadapter(getContext(),cartlist);
        cartrecycle.setLayoutManager(new LinearLayoutManager(getContext()));
        Currentuser = mAuth.getInstance().getCurrentUser();
        String id = Currentuser.getUid();
        databaseReference = FirebaseDatabase.getInstance().getReference("cartdetail").child(id);
        cartrecycle.setAdapter(mycartadapter);
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    mycartmodel mycartmodel = dataSnapshot.getValue(mycartmodel.class);
                    cartlist.add(mycartmodel);
                }
                mycartadapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        orderplacedbtn = view.findViewById(R.id.orderplacedbutton);
        String aamount ="1";
        int amount = Math.round(Float.parseFloat(aamount)*100);
        orderplacedbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // INitialize razorpay checkout
                Checkout checkout = new Checkout();

                // set key id
                checkout.setKeyID("rzp_live_fExzrPbduwZelU");

                // set image
                checkout.setImage(R.drawable.add);

                // initialize jason object
                JSONObject object = new JSONObject();
                try {
                    // to put name
                    object.put("name", "SATYAM GUPTA");

                    // put description
                    object.put("description", "Test payment");

                    // to set theme color
                    object.put("theme.color", "");

                    // put the currency
                    object.put("currency", "INR");

                    // put amount
                    object.put("amount", amount);

                    // put mobile number
                    object.put("prefill.contact", "9284064503");

                    // put email
                    object.put("prefill.email", "chaitanyamunje@gmail.com");

                    // open razorpay to checkout activity
                    checkout.open(getActivity(), object);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        return view;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // This callback will omly be called when MYfragmenr is atleast started
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                Intent intent = new Intent(getActivity(),MainActivity2.class);
                startActivity(intent);
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this,callback);
    }

    @Override
    public void onPaymentSuccess(String s) {
        Toast.makeText(getContext(), "Payment is successful : " + s, Toast.LENGTH_SHORT).show();
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
        alertDialog.setTitle("Payment id");
        alertDialog.setMessage(s);
        alertDialog.show();
    }

    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(getContext(), "Payment Failed due to error : " + s, Toast.LENGTH_SHORT).show();
    }
}