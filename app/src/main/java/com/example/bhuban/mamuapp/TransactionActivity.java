/*package com.example.bhuban.mamuapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.example.bhuban.mamuapp.Users;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class TransactionActivity extends AppCompatActivity
{

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    TextView providerId;
    TextView uid;
    TextView name;
    TextView emailId;
    ListView listViewUser;
    List<Users> usersList;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        mDatabase = FirebaseDatabase.getInstance().getReference("users");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);
        uid = findViewById(R.id.uidText);
        name = findViewById(R.id.nameText);
        emailId = findViewById(R.id.emailIDText);
        listViewUser = findViewById(R.id.listViewuser);
        usersList = new ArrayList<>();

    }

    @Override
    protected void onStart()
    {
        super.onStart();
        mDatabase.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                for(DataSnapshot usersnapshot: dataSnapshot.getChildren())
                {
                    Users shopkeeper = usersnapshot.getValue(Users.class);
                    usersList.add(shopkeeper);
                }

                //CustomerList adapter = new CustomerList(TransactionActivity.this, customerList);
                //listViewUser.setAdapter(adapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
*/