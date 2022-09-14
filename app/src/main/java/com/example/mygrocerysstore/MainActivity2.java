package com.example.mygrocerysstore;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity2 extends AppCompatActivity {
    private static final String COMMON_TAG ="Orientationchange" ;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    ConstraintLayout constraintLayout;
    LinearLayout logincard,forgotcard,registercard,uuserdeatil;
    private FirebaseAuth mAuth;
    private FirebaseUser Currentuser;
    // login add
    private EditText loginemailText;
    private EditText loginpasswordtext;
    private Button loginbtn;
    private Button registerbtn;
    private ProgressBar loginprogressBar;
    private CheckBox logincheckbox;
    private TextView forgotpassword;

    // register add
    private EditText registeremailText;
    private EditText registerpasswordtext;
    private EditText registerconfirmpasswordtext;
    private Button accountregisterbtn;
    private Button reg_login_btn;
    private ProgressBar registerprogressBar;
    private CheckBox registercheckbox;

    // forgot add
    private EditText resetemail;
    private Button resetpassword;
    private String eemail;
    private ProgressBar resetprogressBar;
    private TextView backforgot;

    // user detail add
    private  EditText username,usermob,useralternatemob,useremail,userstate,usercity,userstreet;
    Button submituserdetail;
    Userdetail userdetail ;
    ProgressBar detailprogressbar;

    // splash
    LinearLayout splashscreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        constraintLayout = findViewById(R.id.constarint3);
//        constraintLayout.setVisibility(View.INVISIBLE);
        drawerLayout = findViewById(R.id.drawerlayout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);
        logincard = findViewById(R.id.logincardview);
        forgotcard = findViewById(R.id.forgotcardview);
        registercard = findViewById(R.id.registercardview);

        // login id
        loginemailText= findViewById(R.id.login_email);
        loginpasswordtext= findViewById(R.id.login_password);
        loginbtn = findViewById(R.id.login_button);
        registerbtn = findViewById(R.id.login_register_button);
        loginprogressBar = findViewById(R.id.login_bar);
        logincheckbox = findViewById(R.id.login_checkbox);
        forgotpassword = findViewById(R.id.forgotpassword);

        // register id
        registeremailText= findViewById(R.id.register_email);
        registerpasswordtext= findViewById(R.id.register_password);
        registerconfirmpasswordtext= findViewById(R.id.registerconfirm_password);
        reg_login_btn = findViewById(R.id.register_login_button);
        accountregisterbtn = findViewById(R.id.register_button);
        registerprogressBar = findViewById(R.id.register_bar);
        registercheckbox = findViewById(R.id.register_checkbox);

        // forgot id
        resetpassword = findViewById(R.id.reset_button);
        resetemail = findViewById(R.id.reset_email);
        resetprogressBar = findViewById(R.id.reset_bar);
        backforgot = findViewById(R.id.backforgot);

        // detail ui
        uuserdeatil = findViewById(R.id.userdetail);
        username = findViewById(R.id.username);
        usermob = findViewById(R.id.usermob);
        useralternatemob = findViewById(R.id.useralternatemob);
        useremail = findViewById(R.id.useremail);
        userstate = findViewById(R.id.userstate);
        usercity = findViewById(R.id.usercity);
        userstreet = findViewById(R.id.userstreet);
        submituserdetail = findViewById(R.id.submituserdetail);
        userdetail = new Userdetail();
        detailprogressbar = findViewById(R.id.submitdeatil_bar);

        // splashscreen
        splashscreen = findViewById(R.id.splashscreenn);


        mAuth = FirebaseAuth.getInstance();


        getSupportFragmentManager().beginTransaction().replace(R.id.container,new Homefragment()).commit();

//        getSupportFragmentManager().beginTransaction().replace(R.id.container,new Homefragment()).commit();

        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.opendrawer, R.string.closedrawer);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
//                FragmentManager fragmentManager = getFragmentManager();
//                Bundle bundle = new Bundle();
//                getMenuInflater().inflate(R.menu.mymenu,menu);

                int id = item.getItemId();
                if (id == R.id.nav_home) {
                    Toast.makeText(MainActivity2.this, "Home selected", Toast.LENGTH_SHORT).show();
                    getSupportFragmentManager().beginTransaction().replace(R.id.container,new Homefragment()).commit();
                }
                if (id == R.id.nav_profile) {
                    Toast.makeText(MainActivity2.this, "profile selected", Toast.LENGTH_SHORT).show();
                    getSupportFragmentManager().beginTransaction().replace(R.id.container,new Profilefragment()).commit();
                }
                if (id == R.id.nav_Category) {
                    Toast.makeText(MainActivity2.this, "This feature is not update", Toast.LENGTH_SHORT).show();
//                    getSupportFragmentManager().beginTransaction().replace(R.id.container,new Categoryfragment()).commit();
                }
                if (id == R.id.nav_offer) {
                    Toast.makeText(MainActivity2.this, "This feature is not update", Toast.LENGTH_SHORT).show();
//                    getSupportFragmentManager().beginTransaction().replace(R.id.container,new Offerfragment()).commit();
                }
                if (id == R.id.nav_newprodect) {
                    Toast.makeText(MainActivity2.this, "This feature is not update", Toast.LENGTH_SHORT).show();
//                    getSupportFragmentManager().beginTransaction().replace(R.id.container,new Newproductfragment()).commit();
                }
                if (id == R.id.nav_mycart) {
                    Toast.makeText(MainActivity2.this, "mycart selected", Toast.LENGTH_SHORT).show();
                    getSupportFragmentManager().beginTransaction().replace(R.id.container,new Mycartfragment()).commit();
                }
                if (id == R.id.nav_myorder) {
                    Toast.makeText(MainActivity2.this, "This feature is not update", Toast.LENGTH_SHORT).show();
//                    getSupportFragmentManager().beginTransaction().replace(R.id.container,new Myorderfragment()).commit();
                }
                if (id == R.id.nav_logout) {
                    Toast.makeText(MainActivity2.this, "Account Logout", Toast.LENGTH_SHORT).show();
                    Currentuser = FirebaseAuth.getInstance().getCurrentUser();
                    mAuth.signOut();
                    constraintLayout.setVisibility(View.INVISIBLE);
                    logincard.setVisibility(View.VISIBLE);
                    forgotcard.setVisibility(View.INVISIBLE);
                    registercard.setVisibility(View.INVISIBLE);
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });

        // login work

        logincheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    loginpasswordtext.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                else {
                    loginpasswordtext.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });

        forgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registercard.setVisibility(View.INVISIBLE);
                logincard.setVisibility(View.INVISIBLE);
                forgotcard.setVisibility(View.VISIBLE);
            }
        });

        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logincard.setVisibility(View.INVISIBLE);
                forgotcard.setVisibility(View.INVISIBLE);
                registercard.setVisibility(View.VISIBLE);
                loginemailText.setText("");
                loginpasswordtext.setText("");
            }
        });

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String loginemail = loginemailText.getText().toString();
                String loginpassword = loginpasswordtext.getText().toString();
                if(!TextUtils.isEmpty(loginemail) || !TextUtils.isEmpty(loginpassword)){
                    loginprogressBar.setVisibility(View.VISIBLE);

                    mAuth.signInWithEmailAndPassword(loginemail,loginpassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                loginemailText.setText("");
                                loginpasswordtext.setText("");
                                loginprogressBar.setVisibility(View.INVISIBLE);
                                logincard.setVisibility(View.INVISIBLE);
                                forgotcard.setVisibility(View.INVISIBLE);
                                registercard.setVisibility(View.INVISIBLE);
                                constraintLayout.setVisibility(View.VISIBLE);
                                getSupportFragmentManager().beginTransaction().replace(R.id.container,new Homefragment()).commit();

                            }
                            else{
                                String Error = task.getException().getMessage();
                                Toast.makeText(MainActivity2.this, "Error :- "+Error, Toast.LENGTH_SHORT).show();
                                loginprogressBar.setVisibility(View.INVISIBLE);
                            }
                        }
                    });

                }
            }
        });


        // register work

        registercheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    registerpasswordtext.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    registerconfirmpasswordtext.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                else {
                    registerpasswordtext.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    registerconfirmpasswordtext.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });

        reg_login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logincard.setVisibility(View.VISIBLE);
                forgotcard.setVisibility(View.INVISIBLE);
                registercard.setVisibility(View.INVISIBLE);
                registeremailText.setText("");
                registerpasswordtext.setText("");
                registerconfirmpasswordtext.setText("");
            }
        });

        accountregisterbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = registeremailText.getText().toString();
                String password = registerpasswordtext.getText().toString();
                String confirmpassword = registerconfirmpasswordtext.getText().toString();

                if(!TextUtils.isEmpty(email) || !TextUtils.isEmpty(password) || !TextUtils.isEmpty(confirmpassword)){
                    if (password.equals(confirmpassword)) {
                        registerprogressBar.setVisibility(View.VISIBLE);

                        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    registeremailText.setText("");
                                    registerpasswordtext.setText("");
                                    registerconfirmpasswordtext.setText("");
                                    registerprogressBar.setVisibility(View.INVISIBLE);
                                    logincard.setVisibility(View.INVISIBLE);
                                    forgotcard.setVisibility(View.INVISIBLE);
                                    registercard.setVisibility(View.INVISIBLE);
//                                    constraintLayout.setVisibility(View.VISIBLE);
                                    uuserdeatil.setVisibility(View.VISIBLE);
//                                    getSupportFragmentManager().beginTransaction().replace(R.id.container,new Homefragment()).commit();
                                }
                                else{
                                    String Error = task.getException().getMessage();
                                    Toast.makeText(MainActivity2.this, "Error :- "+Error, Toast.LENGTH_SHORT).show();
                                }
                                registerprogressBar.setVisibility(View.INVISIBLE);
                            }
                        });
                    }else{
                        Toast.makeText(MainActivity2.this, "Confirmpassword and passwored does't Match", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        // forgot work

        backforgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logincard.setVisibility(View.VISIBLE);
                forgotcard.setVisibility(View.INVISIBLE);
                registercard.setVisibility(View.INVISIBLE);
            }
        });

        resetpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetprogressBar.setVisibility(View.VISIBLE);
                eemail = resetemail.getText().toString();
                if(eemail.isEmpty()){
                    resetemail.setError("Required");
                }
                else {
                    mAuth.sendPasswordResetEmail(eemail).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(MainActivity2.this, "Check your email spam and reset password", Toast.LENGTH_SHORT).show();
                                logincard.setVisibility(View.VISIBLE);
                                forgotcard.setVisibility(View.INVISIBLE);
                                registercard.setVisibility(View.INVISIBLE);
                            }
                            else{
                                String Error = task.getException().getMessage();
                                Toast.makeText(getApplicationContext(), "Error :- "+Error, Toast.LENGTH_SHORT).show();
                            }
                            resetprogressBar.setVisibility(View.INVISIBLE);
                        }
                    });
                }
            }
        });


        submituserdetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                detailprogressbar.setVisibility(View.VISIBLE);
                userdetail.setName(username.getText().toString());
                userdetail.setMobile(usermob.getText().toString());
                userdetail.setAmobile(useralternatemob.getText().toString());
                userdetail.setEmail(useremail.getText().toString());
                userdetail.setState(userstate.getText().toString());
                userdetail.setCity(usercity.getText().toString());
                userdetail.setStreet(userstreet.getText().toString());
                uuserdeatil.setVisibility(View.INVISIBLE);
                detailprogressbar.setVisibility(View.INVISIBLE);
                constraintLayout.setVisibility(View.VISIBLE);
                username.setText("");
                usermob.setText("");
                useralternatemob.setText("");
                useremail.setText("");
                userstate.setText("");
                userstreet.setText("");
                usercity.setText("");
                Currentuser = mAuth.getInstance().getCurrentUser();
                String id = Currentuser.getUid();
                firebaseDatabase = FirebaseDatabase.getInstance();
                databaseReference = firebaseDatabase.getReference("Data");
                databaseReference.child(id).setValue(userdetail);


            }
        });


    }
    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else{
            super.onBackPressed();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Currentuser = FirebaseAuth.getInstance().getCurrentUser();
        if(Currentuser==null){
            constraintLayout.setVisibility(View.INVISIBLE);
            logincard.setVisibility(View.VISIBLE);
        }
//        getSupportFragmentManager().beginTransaction().replace(R.id.container,new Homefragment()).commit();
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE){
            Log.i(COMMON_TAG,"landscape");
        }
        else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            Log.i(COMMON_TAG,"portrait");
        }
    }

}