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

import java.util.List;

public class AccountActivity extends AppCompatActivity
{
    public  static final String USER_ID = "userid";
    public  static final String USER_NAME= "username";
    public static  final  String USER_TYPE = "userType";


    private Button mLogoutButton;
    private Button transactionButton;
    private Button shopRegButton;
    private  Button developerButton;
    private  Button infoCustomerButton;
    private FirebaseAuth mAuth;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private DatabaseReference mDatabase;
    DatabaseReference userNameRef;
    String providerId;
    String uid;
    String name;
    String passpass;
    String emailId;
    String fireUId;
    String checkUid;
    String shopNameText;
    List<Users> usersList;
    String userType;



    private Boolean exit = false;

    public void onBackPressed()
    {
        if (exit)
        {
            Intent intent = new Intent(this, AccountActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            finish(); // finish activity
        } else
        {
            Toast.makeText(this, "Press Back again to Exit.",
                    Toast.LENGTH_SHORT).show();
            exit = true;
            new Handler().postDelayed(new Runnable()
            {
                @Override
                public void run()
                {
                    exit = false;
                }
            }, 3 * 1000);

        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        userNameRef = mDatabase.child("users").child(user.getUid());
        userNameRef.addListenerForSingleValueEvent(eventListener);
        Intent intent = getIntent();
        userType = intent.getStringExtra(FacebookActivity.USER_TYPE);
        mLogoutButton =  findViewById(R.id.logoutButton);
        transactionButton = findViewById(R.id.transactionButton);
        shopRegButton = findViewById(R.id.shopRegButton);
        developerButton = findViewById(R.id.contact);
        infoCustomerButton = findViewById(R.id.infoCustomerButton);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        mLogoutButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                mAuth.signOut();

                LoginManager.getInstance().logOut();
                updateUI();


            }
        });
        transactionButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Intent transactionIntent = new Intent(AccountActivity.this,CustomerActivity.class);
                startActivity(transactionIntent);
                finish();

            }
        });

        shopRegButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent shopRegIntent =  new Intent(AccountActivity.this,ShopRegisterActivity.class);
                shopRegIntent.putExtra(USER_TYPE,userType);
                startActivity(shopRegIntent);


            }
        });

        developerButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(AccountActivity.this,DeveloperContact.class);
                startActivity(intent);


            }
        });


        FirebaseUser currentUser = mAuth.getCurrentUser();

        for (UserInfo profile : currentUser.getProviderData())
        {
            passpass = profile.getUid();

        }
        infoCustomerButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent1 = new Intent(AccountActivity.this,InfoCustomerActivity.class);
                intent1.putExtra(USER_ID,passpass);
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


    @Override
    public void onStart()
    {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
    }

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
        Toast.makeText(AccountActivity.this,"You are logged out ",Toast.LENGTH_LONG).show();
        Intent accountIntent = new Intent(AccountActivity.this,FirstActivity.class);
        startActivity(accountIntent);
        finish();
    }

}
