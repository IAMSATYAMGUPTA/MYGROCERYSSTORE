package com.example.mygrocerysstore;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class vegetables extends Fragment {
    RecyclerView allvegetablerecycle;
    ArrayList<allvegetablemodel> valist;
    allvegetableadapter allvegetableadapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_vegetables, container, false);
        allvegetablerecycle = view.findViewById(R.id.allvegetablerecycle);
        valist = new ArrayList<>();
        allvegetableadapter = new allvegetableadapter(valist,getContext());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2);
        allvegetablerecycle.setLayoutManager(gridLayoutManager);
        DatabaseReference allvgrefrence = FirebaseDatabase.getInstance().getReference().child("viewallvegetable");
        allvegetablerecycle.setAdapter(allvegetableadapter);
        allvgrefrence.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    allvegetablemodel allvegetablemodel = dataSnapshot.getValue(allvegetablemodel.class);
                    valist.add(allvegetablemodel);
                }
                allvegetableadapter.notifyDataSetChanged();
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
}