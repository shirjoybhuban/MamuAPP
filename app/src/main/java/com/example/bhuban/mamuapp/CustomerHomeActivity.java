package com.example.bhuban.mamuapp;


import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CustomerHomeActivity extends AppCompatActivity
{
    private DatabaseReference mDatabase;
    DatabaseReference userNameRef;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private FirebaseAuth mAuth;

    public static  final  String NAME = "name";


    String providerId;
    String uid;
    String name;
    String namepass;
    String passname;

    String emailId;
    String fireUId;
    String userType;

    private Button logoutButton;
    private Button showTransactionButton;
    private Button permissionButton;
    private Button developerButton;



    private Boolean exit = false;
    @Override
    public void onBackPressed() {
        if (exit)
        {
            Intent intent = new Intent(this, CustomerHomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            finish(); // finish activity
        }
        else
        {
            Toast.makeText(this, "Press Back again to Exit.",
                    Toast.LENGTH_SHORT).show();
            exit = true;
            new Handler().postDelayed(new Runnable()
            {
                @Override
                public void run() {
                    exit = false;
                }
            }, 3 * 1000);

        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_home);

        Intent intent = getIntent();
        userType = intent.getStringExtra(FacebookActivity.USER_TYPE);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        userNameRef = mDatabase.child("users").child(user.getUid());
        userNameRef.addListenerForSingleValueEvent(eventListener);
        mAuth = FirebaseAuth.getInstance();



        logoutButton = findViewById(R.id.customerlogout3);
        showTransactionButton = findViewById(R.id.showtransaction);
        permissionButton = findViewById(R.id.permission);
        developerButton = findViewById(R.id.developer);

        logoutButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                mAuth.signOut();

                LoginManager.getInstance().logOut();
                updateUI();
            }
        });

        developerButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(CustomerHomeActivity.this,DeveloperContact.class);
                startActivity(intent);
            }
        });



        permissionButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(CustomerHomeActivity.this,CustomerPermissionActivity.class);
                FirebaseUser currentUser = mAuth.getCurrentUser();
                if(currentUser == null)
                {
                    updateUI();
                }
                if (currentUser != null)
                {
                    for (UserInfo profile : currentUser.getProviderData())
                    {
                        namepass = profile.getDisplayName();
                    }


                }

                intent.putExtra(NAME,namepass);
                startActivity(intent);
                finish();

            }
        });

        showTransactionButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent1 = new Intent(CustomerHomeActivity.this,CustomerTransactionActivity.class);
                FirebaseUser currentUser = mAuth.getCurrentUser();
                if(currentUser == null)
                {
                    updateUI();
                }
                if (currentUser != null)
                {
                    for (UserInfo profile : currentUser.getProviderData())
                    {
                        namepass = profile.getDisplayName();
                    }


                }

                intent1.putExtra(NAME,namepass);
                startActivity(intent1);
                finish();

            }
        });
    }

    ValueEventListener eventListener = new ValueEventListener()
    {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot)
        {
            if(!dataSnapshot.exists())
            {
                //create new user
                FirebaseUser currentUser = mAuth.getCurrentUser();
                if(currentUser == null)
                {
                    updateUI();
                }
                if (currentUser != null)
                {
                    for (UserInfo profile : currentUser.getProviderData())
                    {
                        // Id of the provider (ex: google.com)
                        providerId = profile.getProviderId();

                        // UID specific to the provider
                        uid = profile.getUid();

                        // Name, email address, and profile photo Url
                        name = profile.getDisplayName();
                        emailId = profile.getEmail();
                    }


                }

                writeNewUser(name,emailId,uid);
            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError)
        {

        }
    };

    private void writeNewUser(String name, String email, String userId)
    {
        FirebaseUser USER = FirebaseAuth.getInstance().getCurrentUser();
        fireUId = USER.getUid();
        Users users = new Users(name, email, userId);
        users.setFireId(fireUId);
        users.setUserType(userType);
        mDatabase.child("users").child(fireUId).setValue(users);
    }

    private void updateUI()
    {
        Toast.makeText(CustomerHomeActivity.this,"You are logged out ",Toast.LENGTH_LONG).show();
        Intent accountIntent = new Intent(CustomerHomeActivity.this,FacebookActivity.class);
        startActivity(accountIntent);
        finish();
    }
}
