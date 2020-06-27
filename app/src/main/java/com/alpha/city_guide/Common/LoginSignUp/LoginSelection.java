package com.alpha.city_guide.Common.LoginSignUp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.alpha.city_guide.R;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.hbb20.CountryCodePicker;

public class LoginSelection extends AppCompatActivity {

    CountryCodePicker countryCodePicker;
    TextInputLayout phoneNumber, password;
    RelativeLayout login_progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retailer_login_selection);

        //hooks
        countryCodePicker = findViewById(R.id.login_country_code_picker);
        phoneNumber = findViewById(R.id.login_phone_number);
        password = findViewById(R.id.login_password);
        login_progress = findViewById(R.id.login_progress);

    }

    public void letUserLogin(View view) {

  //      if (!validateFields()) {
    //        return;
      //      ;
        //}

        login_progress.setVisibility(View.VISIBLE);
        String _phoneNumber = phoneNumber.getEditText().getText().toString().trim();
        final String _password = password.getEditText().getText().toString().trim();

        if (_phoneNumber.charAt(0) == '0') {
            _phoneNumber = _phoneNumber.substring(1);
        }
        final String _completePhoneNumber = "+" + countryCodePicker.getFullNumber() + _phoneNumber;

        //Database Query
        Query checkUser = FirebaseDatabase.getInstance().getReference("Users").orderByChild("phoneNo").equalTo(_completePhoneNumber);

        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {
                    phoneNumber.setError(null);
                    phoneNumber.setErrorEnabled(false);

                    String systemPassword = dataSnapshot.child(_completePhoneNumber).child("password").getValue(String.class);

                    if (systemPassword.equals(_password)) {

                        phoneNumber.setError(null);
                        phoneNumber.setErrorEnabled(false);

                        String _fullname=dataSnapshot.child(_completePhoneNumber).child("fullName").getValue(String.class);
                        String _email=dataSnapshot.child(_completePhoneNumber).child("email").getValue(String.class);
                        String _phoneNo=dataSnapshot.child(_completePhoneNumber).child("phoneNo").getValue(String.class);
                        String _dateofBirth=dataSnapshot.child(_completePhoneNumber).child("date").getValue(String.class);

                        Toast.makeText(LoginSelection.this, _fullname+"\n"+_email+"\n"+_phoneNo+"\n"+_dateofBirth, Toast.LENGTH_SHORT).show();

                    } else {
                        login_progress.setVisibility(View.GONE);
                        Toast.makeText(LoginSelection.this, "Password is incorrect", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    login_progress.setVisibility(View.GONE);
                    Toast.makeText(LoginSelection.this, "No such User Exist", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                login_progress.setVisibility(View.GONE);
                Toast.makeText(LoginSelection.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
