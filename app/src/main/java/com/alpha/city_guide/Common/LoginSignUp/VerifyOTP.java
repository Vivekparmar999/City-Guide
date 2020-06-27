package com.alpha.city_guide.Common.LoginSignUp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.alpha.city_guide.Databases.UserHelperClass;
import com.alpha.city_guide.R;
import com.chaos.view.PinView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.TimeUnit;

public class VerifyOTP extends AppCompatActivity {

    PinView pinFromUser;
    String id;
    TextView resend;
    String fullnameI, emailI, usernameI, passwordI, dateI, genderI, phonenumberI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_otp);

        resend = findViewById(R.id.resend);
        pinFromUser = findViewById(R.id.pinview);


        Intent intent = getIntent();
        fullnameI = intent.getStringExtra("fullnameI");
        emailI = intent.getStringExtra("emailI");
        usernameI = intent.getStringExtra("usernameI");
        passwordI = intent.getStringExtra("passwordI");
        dateI = intent.getStringExtra("dateI");
        genderI = intent.getStringExtra("genderI");
        phonenumberI = intent.getStringExtra("phonenumberI");


        sendVerificationCode();

        resend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendVerificationCode();
            }
        });

    } // class over

    public void callNextScreenFromOTP(View view) {

        String code = pinFromUser.getText().toString();
        if (!code.isEmpty()) {
            PhoneAuthCredential credential = PhoneAuthProvider.getCredential(id, code);
            signInWithPhoneAuthCredential(credential);
        }

    }


    private void sendVerificationCode() {

        new CountDownTimer(60000, 1000) {

            @Override
            public void onTick(long l) {

                String countdown = "" + l / 1000;
                resend.setText(countdown);
                resend.setEnabled(false);
            }

            @Override
            public void onFinish() {

                resend.setText("Resend");
                resend.setEnabled(true);

            }
        }.start();

        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phonenumberI,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                this,               // Activity (for callback binding)
                new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    @Override
                    public void onCodeSent(String id, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                        VerifyOTP.this.id = id;

                    }

                    @Override
                    public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                        signInWithPhoneAuthCredential(phoneAuthCredential);
                    }

                    @Override
                    public void onVerificationFailed(FirebaseException e) {
                        Toast.makeText(VerifyOTP.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });        // OnVerificationStateChangedCallbacks
    }


    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            storeNewUserData();
                            startActivity(new Intent(VerifyOTP.this, ForgetPasswordSuccessMessage.class));
                            finish();
                        } else {
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                Toast.makeText(VerifyOTP.this, "invalid", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                });
    }

    private void storeNewUserData() {
        FirebaseDatabase rootNode = FirebaseDatabase.getInstance();
        DatabaseReference reference = rootNode.getReference("Users");
        UserHelperClass addNewUser = new UserHelperClass(fullnameI, usernameI, emailI, phonenumberI, passwordI, dateI, genderI);
        reference.child(phonenumberI).setValue(addNewUser);
    }

}