package com.example.mygrocerysstore;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class Profilefragment extends Fragment {
    FirebaseDatabase database;
    DatabaseReference reference;
    FirebaseAuth mAuth;
    FirebaseUser Currentuser;
    TextView name,mob,amob,email,state,city,street;
    Button changedetail;
    LinearLayout showprodeatail;
    ProgressBar searchuserdetail;
    LinearLayout updatedetail;

    // user detail add
    private EditText usernamep,usermobp,useralternatemobp,useremailp,userstatep,usercityp,userstreetp;
    Button submituserdetailp;
    Userdetail userdetail ;
    ProgressBar detailprogressbarp;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profilefragment, container, false);
        name = view.findViewById(R.id.profilename);
        mob = view.findViewById(R.id.profilemob);
        amob = view.findViewById(R.id.profileamob);
        email = view.findViewById(R.id.profilemail);
        state = view.findViewById(R.id.profilestate);
        city = view.findViewById(R.id.profilecity);
        street = view.findViewById(R.id.profilestreet);
        changedetail = view.findViewById(R.id.profilechangedetail);
        showprodeatail = view.findViewById(R.id.showprofiledetail);
        searchuserdetail = view.findViewById(R.id.profileloadprogressbar);
        updatedetail = view.findViewById(R.id.userdetailp);
        submituserdetailp = view.findViewById(R.id.submituserdetailp);


        // update deatil id
        userdetail = new Userdetail();
        usernamep = view.findViewById(R.id.usernamep);
        usermobp = view.findViewById(R.id.usermobp);
        useralternatemobp = view.findViewById(R.id.useralternatemobp);
        useremailp = view.findViewById(R.id.useremailp);
        userstatep = view.findViewById(R.id.userstatep);
        usercityp = view.findViewById(R.id.usercityp);
        userstreetp = view.findViewById(R.id.userstreetp);
        detailprogressbarp = view.findViewById(R.id.submitdeatil_barp);


        searchuserdetail.setVisibility(View.VISIBLE);

        Currentuser = mAuth.getInstance().getCurrentUser();

        String id = Currentuser.getUid();
        reference =FirebaseDatabase.getInstance().getReference("Data").child(id);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                Userdetail userdetail = snapshot.getValue(Userdetail.class);

//                userdetail.setName("satyam");

//                Toast.makeText(getContext(), ""+userdetail.getName(), Toast.LENGTH_SHORT).show();
                name.setText(userdetail.getName());
                mob.setText(userdetail.getMobile());
                amob.setText(userdetail.getAmobile());
                email.setText(userdetail.getEmail());
                state.setText(userdetail.getState());
                city.setText(userdetail.getCity());
                street.setText(userdetail.getStreet());
                showprodeatail.setVisibility(View.VISIBLE);
                searchuserdetail.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        changedetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showprodeatail.setVisibility(View.INVISIBLE);
                searchuserdetail.setVisibility(View.INVISIBLE);
                updatedetail.setVisibility(View.VISIBLE);
            }
        });
        submituserdetailp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                detailprogressbarp.setVisibility(View.VISIBLE);
                userdetail.setName(usernamep.getText().toString());
                userdetail.setMobile(usermobp.getText().toString());
                userdetail.setAmobile(useralternatemobp.getText().toString());
                userdetail.setEmail(useremailp.getText().toString());
                userdetail.setState(userstatep.getText().toString());
                userdetail.setCity(usercityp.getText().toString());
                userdetail.setStreet(userstreetp.getText().toString());
                usernamep.setText("");
                usermobp.setText("");
                useralternatemobp.setText("");
                useremailp.setText("");
                userstatep.setText("");
                userstreetp.setText("");
                usercityp.setText("");
                Currentuser = mAuth.getInstance().getCurrentUser();
                String id = Currentuser.getUid();
                database = FirebaseDatabase.getInstance();
                reference = database.getReference("Data");
                reference.child(id).setValue(userdetail);
                updatedetail.setVisibility(View.INVISIBLE);
                showprodeatail.setVisibility(View.VISIBLE);
                detailprogressbarp.setVisibility(View.INVISIBLE);
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
}