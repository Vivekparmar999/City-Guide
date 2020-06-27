package com.alpha.city_guide.Common.LoginSignUp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alpha.city_guide.R;
import com.google.android.material.textfield.TextInputLayout;
import com.hbb20.CountryCodePicker;

public class SignUp3rdClass extends AppCompatActivity {


    ImageView backbtn;
    Button next, login;
    TextView titleText;

    TextInputLayout phonenumber;
    CountryCodePicker countryCodePicker;

    String fullnameI,emailI,usernameI,passwordI,dateI,genderI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up3rd_class);
        backbtn = findViewById(R.id.Signup_back_button);
        next = findViewById(R.id.signup_next_btn);
        login = findViewById(R.id.signup_login_btn);
        titleText = findViewById(R.id.signup_title_text);

        //hooks
        countryCodePicker=findViewById(R.id.country_code_picker);
        phonenumber=findViewById(R.id.signup_phone_number);

        Intent intent=getIntent();
        fullnameI=intent.getStringExtra("fullnameI");
        emailI=intent.getStringExtra("emailI");
        usernameI=intent.getStringExtra("usernameI");
        passwordI=intent.getStringExtra("passwordI");
        dateI=intent.getStringExtra("dateI");
        genderI=intent.getStringExtra("genderI");

    }

    public void callNextSignupScreen(View view) {

        if(!validatephonenumber())
        {return;}

        String enteredphonenumber=phonenumber.getEditText().getText().toString().trim();
        String phonenumberI="+"+countryCodePicker.getSelectedCountryCode()+enteredphonenumber;

        Intent intent=new Intent(getApplicationContext(),VerifyOTP.class);

        intent.putExtra("fullnameI",fullnameI);
        intent.putExtra("emailI",emailI);
        intent.putExtra("usernameI",usernameI);
        intent.putExtra("passwordI",passwordI);
        intent.putExtra("genderI",genderI);
        intent.putExtra("dateI",dateI);
      intent.putExtra("phonenumberI",phonenumberI);

        Pair[] pairs = new Pair[4];
        pairs[0] = new Pair<View,String>(backbtn,"transition_back_btn");
        pairs[1] = new Pair<View,String>(next,"callNextSignupScreen");
        pairs[2] = new Pair<View,String>(login,"callLoginSignUpScreen");
        pairs[3] = new Pair<View,String>(titleText,"transition_title_btn");

        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SignUp3rdClass.this,pairs);
        startActivity(intent,options.toBundle());
    }

    private boolean validatephonenumber() {

        String val = phonenumber.getEditText().getText().toString().trim();
      //  String checknumber="^[2-9]{2}[0-9]{8}$";

        if (val.isEmpty()) {
            phonenumber.setError("Field can not Empty");
            return false;
        }
        else if(val.length()!=10){
            phonenumber.setError("Only 10 Digit allowed");
            return  false;
        }
      //  else if (!val.matches(checknumber)){
     //       phonenumber.setError("Enter only 10 digit number");
     //       return false;
     //   }
        else {
            phonenumber.setError(null);
            phonenumber.setErrorEnabled(false);
            return true;
        }

    }

    public void callLoginSignUpScreen(View view) {
    }
}
