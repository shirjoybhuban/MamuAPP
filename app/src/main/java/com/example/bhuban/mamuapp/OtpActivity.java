package com.example.bhuban.mamuapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class OtpActivity extends AppCompatActivity
{

    private EditText phoneNoText;
    private EditText verificationCodeText;
    private Button verificationButton;
    private ProgressBar phoneNoProgress;
    private ProgressBar verificationCodeProgress;
    private TextView mErrorText;
    private int buttonType = 0;

    private FirebaseAuth mAuth;



    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    private String  mVerificationId;
    private PhoneAuthProvider.ForceResendingToken mResendToken;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);


        phoneNoText = (EditText) findViewById(R.id.phoneNoText);

        verificationCodeText = (EditText) findViewById(R.id.verificaionCodeText);

        verificationButton = (Button) findViewById(R.id.verificationButton);

        phoneNoProgress = (ProgressBar) findViewById(R.id.phoneProgress);
        verificationCodeProgress = (ProgressBar) findViewById(R.id.verificationProgress);

        mErrorText = (TextView) findViewById(R.id.errorText);

        mAuth = FirebaseAuth.getInstance();


        verificationButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                if (buttonType == 0)
                {
                    phoneNoProgress.setVisibility(View.VISIBLE);
                    phoneNoText.setEnabled(false);
                    verificationButton.setEnabled(false);

                    String phoneNumber = phoneNoText.getText().toString();

                    PhoneAuthProvider.getInstance().verifyPhoneNumber(
                            phoneNumber,
                            60,
                            TimeUnit.SECONDS,
                            OtpActivity.this,
                            mCallbacks

                    );
                }
                else
                {
                    verificationButton.setEnabled(false);
                    verificationCodeProgress.setVisibility(View.VISIBLE);

                    String verificationCode = verificationCodeText.getText().toString();

                    PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId,verificationCode);
                    signInWithPhoneAuthCredential(credential);

                }
            }
        });

        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks()
        {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential)
            {
                signInWithPhoneAuthCredential(phoneAuthCredential);
            }

            @Override
            public void onVerificationFailed(FirebaseException e)
            {
                mErrorText.setText("There was some error in verification");
                mErrorText.setVisibility(View.VISIBLE);
            }

            @Override
            public void onCodeSent(String verificationId,
                                   PhoneAuthProvider.ForceResendingToken token) {


                mVerificationId = verificationId;
                mResendToken = token;

                buttonType = 1;

                // ...
                phoneNoProgress.setVisibility(View.INVISIBLE);
                verificationCodeProgress.setVisibility(View.VISIBLE);
                verificationCodeText.setVisibility(View.VISIBLE);

                verificationButton.setText("Verify code");
                verificationButton.setEnabled(true);
            }
        };

    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>()
                {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {
                        if (task.isSuccessful())
                        {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = task.getResult().getUser();

                            Intent accountIntent = new Intent( OtpActivity.this,OtpInfoActivity.class);
                            startActivity(accountIntent);
                            finish();
                            // ...
                        }

                        else
                        {
                            // Sign in failed, display a message and update the UI
                            mErrorText.setText("There was some error in login");
                            mErrorText.setVisibility(View.VISIBLE);
                            if(task.getException() instanceof FirebaseAuthInvalidCredentialsException)
                            {
                                // The verification code entered was invalid
                            }
                        }
                    }
                });
    }
}
