package com.example.mygrocerysstore;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class header_layout extends AppCompatActivity {
    TextView drawername,draweremail;
    FirebaseAuth mAuth;
    FirebaseUser Currentuser;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_header_layout);
        drawername = findViewById(R.id.drawerprofilename);
        draweremail = findViewById(R.id.drawerprofileemail);

        Currentuser = mAuth.getInstance().getCurrentUser();
        String id = Currentuser.getUid();
        reference = FirebaseDatabase.getInstance().getReference("Data").child(id);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Userdetail userdetail = snapshot.getValue(Userdetail.class);
                drawername.setText(userdetail.getName());
                draweremail.setText(userdetail.getEmail());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}