package com.example.bhuban.mamuapp;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class FirstActivity extends AppCompatActivity
{
    private Button routeCustomerbtn;
    private Button routeShopbtn;
    public static  final  String USER_TYPE = "userType";


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        routeCustomerbtn = findViewById(R.id.routeCustomerbtn);
        routeShopbtn = findViewById(R.id.routeShopbtn);

        try
        {
            PackageInfo info = getPackageManager().getPackageInfo("com.example.bhuban.mamuapp",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures)
            {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e)
        {

        } catch (NoSuchAlgorithmException e)
        {

        }



        routeCustomerbtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(FirstActivity.this,FacebookActivity.class);
                intent.putExtra(USER_TYPE,"customer");
                startActivity(intent);
                finish();

            }
        });

        routeShopbtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(FirstActivity.this,FacebookActivity.class);
                intent.putExtra(USER_TYPE,"shopkeeper");
                startActivity(intent);
                finish();

            }
        });


    }
}
