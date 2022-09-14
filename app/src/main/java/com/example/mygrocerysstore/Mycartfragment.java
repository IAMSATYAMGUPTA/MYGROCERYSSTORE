package com.example.mygrocerysstore;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class Mycartfragment extends Fragment {
    RecyclerView cartrecycle;
    ArrayList<mycartmodel> cartlist = new ArrayList<>();
    mycartadapter mycartadapter;
    FirebaseUser Currentuser;
    DatabaseReference databaseReference;
    FirebaseAuth mAuth;
    FirebaseDatabase firebaseDatabase;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mycartfragment, container, false);
        cartrecycle = view.findViewById(R.id.mycartrecycle);
        mycartadapter = new mycartadapter(getContext(),cartlist);
        cartrecycle.setLayoutManager(new LinearLayoutManager(getContext()));
        Currentuser = mAuth.getInstance().getCurrentUser();
        String id = Currentuser.getUid();
        databaseReference =FirebaseDatabase.getInstance().getReference("cartdetail").child(id);
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

//    @Override
//    public void onStart() {
//        super.onStart();
//        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container,new Mycartfragment()).commit();
//    }
}