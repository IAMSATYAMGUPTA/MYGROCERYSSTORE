package com.example.mygrocerysstore;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
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


public class Homefragment extends Fragment {

    FirebaseAuth mAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    // top recycle
    RecyclerView toprecycle;
    ArrayList<popularmodel> popularlist;
    popularAdapter popularAdapter;

    // vegetable recycle
    RecyclerView middlerecycle;
    vegetableadapter vegetableadapter;
    ArrayList<vegetablemodel> vegelist;

    //SET Fruit recycle
    RecyclerView thirdrecycle;
    fruitadapter fruitadapter;
    ArrayList<fruitmodel> flist;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_homefragment, container, false);


        // SET TOP IMAGES IN RECYCLERVIEW
        toprecycle = view.findViewById(R.id.toprec);
        popularlist = new ArrayList<>();
        popularAdapter = new popularAdapter(popularlist,getContext());

        // Horizontal view
        toprecycle.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false));
        databaseReference = FirebaseDatabase.getInstance().getReference().child("popularproduct");
        toprecycle.setAdapter(popularAdapter);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    popularmodel popularmodel = dataSnapshot.getValue(popularmodel.class);
                    popularlist.add(popularmodel);
                }
                popularAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        //SET Vegetable IMAGES IN RECYCLERVIEW
        middlerecycle = view.findViewById(R.id.secondrec);
        vegelist = new ArrayList<>();
        vegetableadapter = new vegetableadapter(vegelist,getContext());
        DatabaseReference vegerefrence;
        vegerefrence = FirebaseDatabase.getInstance().getReference().child("vegetable");
        middlerecycle.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false));
        middlerecycle.setAdapter(vegetableadapter);
        vegerefrence.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    vegetablemodel vegetablemodel = dataSnapshot.getValue(vegetablemodel.class);
                    vegelist.add(vegetablemodel);
                }
                vegetableadapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //SET Fruit IMAGES IN RECYCLERVIEW
        thirdrecycle = view.findViewById(R.id.thirdrec);
        flist = new ArrayList<>();
        fruitadapter = new fruitadapter(flist,getContext());
        DatabaseReference fruitrefrence;
        fruitrefrence = FirebaseDatabase.getInstance().getReference().child("fruit");
        thirdrecycle.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false));
        thirdrecycle.setAdapter(fruitadapter);
        fruitrefrence.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    fruitmodel fruitmodel = dataSnapshot.getValue(fruitmodel.class);
                    flist.add(fruitmodel);
                }
                fruitadapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        TextView vegetableview = view.findViewById(R.id.vegetableview);
        TextView fruitview = view.findViewById(R.id.fruitview);
        vegetableview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container,new vegetables()).commit();
            }
        });
        fruitview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container,new fruits()).commit();
            }
        });


        return view;
    }
}