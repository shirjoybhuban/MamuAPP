package com.example.bhuban.mamuapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class ShopRegisterActivity extends AppCompatActivity
{

    private ImageButton addBtn;
    private EditText editTextSname;
    private EditText editTextSaddress;
    DatabaseReference databaseRegister;
    String name;
    String emailId;
    String userID;
    String userType;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_register);
        addBtn = findViewById(R.id.addBtn);
        editTextSname = findViewById(R.id.editTextSname);
        editTextSaddress = findViewById(R.id.editTextSaddress);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        databaseRegister =  FirebaseDatabase.getInstance().getReference("users").child(user.getUid());
        Intent intent = getIntent();
        userType = intent.getStringExtra(AccountActivity.USER_TYPE);

        addBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                shopRegister();
            }
        });



    }

    @Override
    public void onBackPressed()
    {
        this.finish();

    }

    private void shopRegister()
    {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String editTextname =  editTextSname.getText().toString();
        String editTextAddress = editTextSaddress.getText().toString().trim();
        if (!TextUtils.isEmpty(editTextname) && !TextUtils.isEmpty(editTextAddress))
        {
            if (user != null)
            {
                for (UserInfo profile : user.getProviderData())
                {
                    // Name, email address, and profile photo Url
                    name = profile.getDisplayName();
                    emailId = profile.getEmail();
                    userID = profile.getUid();
                }

            }


            Users userShop = new Users(name,emailId,userID);
            userShop.setFireId(user.getUid());
            userShop.setShopName(editTextname);
            userShop.setShopAddress(editTextAddress);
            userShop.setUserType(userType);

            databaseRegister.setValue(userShop);


            Toast.makeText(this, "Shop Information Updated",Toast.LENGTH_LONG).show();


            Intent shopRegIntent =  new Intent(ShopRegisterActivity.this,AccountActivity.class);
            startActivity(shopRegIntent);
            finish();

        }
        else
        {
            Toast.makeText(this, "Please Enter Shop Information",Toast.LENGTH_LONG).show();

        }
    }


}


