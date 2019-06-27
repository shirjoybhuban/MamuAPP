package com.example.bhuban.mamuapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;

public class FacebookActivity extends AppCompatActivity
{


    private CallbackManager mCallbackManager;

    private Button mFacebookButton;
    private Button otpButton;
    private static final  String TAG = "FACELOG";
    String userType;
    public static  final  String USER_TYPE = "userType";
    public static  final  String CUSTOMER_NAME = "customerName";


    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facebook);



        Intent intent = getIntent();
        userType = intent.getStringExtra(FirstActivity.USER_TYPE);


        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        mFacebookButton = (Button) findViewById(R.id.facebookButton);
        otpButton = (Button) findViewById((R.id.otpButton));

        otpButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(FacebookActivity.this,OtpActivity.class));
                finish();
            }
        });


        // Initialize Facebook Login button
        mCallbackManager = CallbackManager.Factory.create();


        mFacebookButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                mFacebookButton.setEnabled(false);

                LoginManager.getInstance().logInWithReadPermissions(FacebookActivity.this, Arrays.asList("email", "public_profile"));
                LoginManager.getInstance().registerCallback(mCallbackManager, new FacebookCallback<LoginResult>()
                {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        Log.d(TAG, "facebook:onSuccess:" + loginResult);
                        handleFacebookAccessToken(loginResult.getAccessToken());
                    }

                    @Override
                    public void onCancel() {
                        Log.d(TAG, "facebook:onCancel");
                        // ...
                    }

                    @Override
                    public void onError(FacebookException error) {
                        Log.d(TAG, "facebook:onError", error);
                        // ...
                    }
                });

            }
        });
    }


    @Override
    public void onStart()
    {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if(currentUser !=null)
        {
            updateUI();
        }

    }

    private void updateUI()
    {
//        Log.d(TAG, userType);
        if(userType.equals("shopkeeper"))
        {
            Toast.makeText(FacebookActivity.this," WELCOME ",Toast.LENGTH_LONG).show();
            Intent accountIntent = new Intent(FacebookActivity.this,AccountActivity.class);
            accountIntent.putExtra(USER_TYPE,userType);
            startActivity(accountIntent);
            finish();

        }
        else
        {
            Toast.makeText(FacebookActivity.this," WELCOME ",Toast.LENGTH_LONG).show();
            Intent accountIntent = new Intent(FacebookActivity.this,CustomerHomeActivity.class);
            accountIntent.putExtra(USER_TYPE,userType);
           // accountIntent.putExtra(CUSTOMER_NAME,userType);
            startActivity(accountIntent);
            finish();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        // Pass the activity result back to the Facebook SDK
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private void handleFacebookAccessToken(AccessToken token)
    {
        Log.d(TAG, "handleFacebookAccessToken:" + token);

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>()
                {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {
                        if (task.isSuccessful())
                        {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();

                            mFacebookButton.setEnabled(true);

                            updateUI();
                        }
                        else
                        {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(FacebookActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();


                            mFacebookButton.setEnabled(true);

                        }
                    }
                });
    }

}
