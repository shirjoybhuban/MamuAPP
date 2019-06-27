package com.example.bhuban.mamuapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class OtpInfoActivity extends AppCompatActivity
{

    private DatabaseReference mDatabase;

    EditText editTextUsername, editTextPassword;
    private FirebaseAuth mAuth;
    private Button logoutButton;
    String providerId;
    String uid;
    String name = "na";
    String emailId ="em";


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_info);
        mDatabase = FirebaseDatabase.getInstance().getReference();

        mAuth = FirebaseAuth.getInstance();

        FirebaseUser currentuser = mAuth.getCurrentUser();
        logoutButton = findViewById(R.id.logoutOtpButton);

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                updateUI();


            }
        });

    }

    @Override
    public void onStart()
    {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser == null)
        {
            updateUI();
        }
        if (currentUser != null)
        {


                // Id of the provider (ex: google.com)
               // providerId = profile.getProviderId();

                // UID specific to the provider
                uid = currentUser.getUid();

                // Name, email address, and profile photo Url
               // name = profile.getDisplayName();
                //emailId = profile.getEmail();



        }
        writeNewUser(name,emailId,uid);

    }
    private void updateUI()
    {
        Toast.makeText(OtpInfoActivity.this,"You are logged out ",Toast.LENGTH_LONG).show();
        Intent accountIntent = new Intent(OtpInfoActivity.this,FacebookActivity.class);
        startActivity(accountIntent);
        finish();
    }

    private void writeNewUser(String name, String email, String userId)
    {
        Users user = new Users(name, email, userId);

        mDatabase.child("users").child(userId).setValue(user);
    }


    }
