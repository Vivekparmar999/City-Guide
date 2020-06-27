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

import com.alpha.city_guide.R;
import com.google.android.material.textfield.TextInputLayout;

public class SignUp extends AppCompatActivity {


    ImageView backbtn;
    Button next, login;
    TextView titleText;


    TextInputLayout fullname, username, email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retailer_sign_up);

        //hooks for animation
        backbtn = findViewById(R.id.Signup_back_button);
        next = findViewById(R.id.signup_next_btn);
        login = findViewById(R.id.signup_login_btn);
        titleText = findViewById(R.id.signup_title_text);

        //hooks for getting data
        fullname = findViewById(R.id.signup_fullname);
        username = findViewById(R.id.signup_username);
        email = findViewById(R.id.signup_email);
        password = findViewById(R.id.signup_password);

    }

    public void callNextSignupScreen(View view) {
        if(!validateFullName()| !validateUserName() | !validateEmail() | !validatePassword()){
            return;
        }

        String fullnameI,usernameI,emailI,passwordI;
        fullnameI=fullname.getEditText().getText().toString();
        usernameI=username.getEditText().getText().toString();
        emailI=email.getEditText().getText().toString();
        passwordI=password.getEditText().getText().toString();

        Intent intent = new Intent(getApplicationContext(), Signup2nd_class.class);
        Pair[] pairs = new Pair[4];
        pairs[0] = new Pair<View, String>(backbtn, "transition_back_btn");
        pairs[1] = new Pair<View, String>(next, "callNextSignupScreen");
        pairs[2] = new Pair<View, String>(login, "callLoginSignUpScreen");
        pairs[3] = new Pair<View, String>(titleText, "transition_title_btn");

        intent.putExtra("fullnameI",fullnameI);
        intent.putExtra("emailI",emailI);
        intent.putExtra("usernameI",usernameI);
        intent.putExtra("passwordI",passwordI);

        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SignUp.this, pairs);
        startActivity(intent, options.toBundle());

    }

    public void callLoginSignUpScreen(View view) {
        if(!validateFullName()| !validateUserName() | !validateEmail() | !validatePassword()){
            return;
        }

        String fullnameI,usernameI,emailI,passwordI;
        fullnameI=fullname.getEditText().getText().toString();
        usernameI=username.getEditText().getText().toString();
        emailI=email.getEditText().getText().toString();
        passwordI=password.getEditText().getText().toString();

        Intent intent = new Intent(getApplicationContext(), Signup2nd_class.class);
        Pair[] pairs = new Pair[4];
        pairs[0] = new Pair<View, String>(backbtn, "transition_back_btn");
        pairs[1] = new Pair<View, String>(next, "callNextSignupScreen");
        pairs[2] = new Pair<View, String>(login, "callLoginSignUpScreen");
        pairs[3] = new Pair<View, String>(titleText, "transition_title_btn");

        intent.putExtra("fullnameI",fullnameI);
        intent.putExtra("emailI",emailI);
        intent.putExtra("usernameI",usernameI);
        intent.putExtra("passwordI",passwordI);

        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SignUp.this, pairs);
        startActivity(intent, options.toBundle());

    }

    private boolean validateFullName() {
        String val = fullname.getEditText().getText().toString().trim();

        if (val.isEmpty()) {
            fullname.setError("Field can not Empty");
            return false;
        } else {
            fullname.setError(null);
            fullname.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateUserName() {
        String val = username.getEditText().getText().toString().trim();
        String checkspace="\\A\\w{1,20}\\z";

        if (val.isEmpty()) {
            username.setError("Field can not Empty");
            return false;
        }
        else if(val.length()>20){
            username.setError("Username is too Large!");
            return  false;
        }
        else if (!val.matches(checkspace)){
            username.setError("No white spaces");
            return false;
        }
        else {
            username.setError(null);
            username.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateEmail() {
        String val = email.getEditText().getText().toString().trim();
        String checkEmail="[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (val.isEmpty()) {
            email.setError("Field can not Empty");
            return false;
        }
        else if (!val.matches(checkEmail)){
            email.setError("Email is invalid");
            return false;
        }
        else {
            email.setError(null);
            email.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validatePassword() {
        String val = password.getEditText().getText().toString().trim();
        String checkpassword="^"+
                //"(?=.*[0-9])"+   //at least 1 digit
                //"(?=.*[a-z])"+   //at least 1 lower case
                //"(?=.*[A-Z])"+   //at least 1 upper case
                "(?=.*[a-zA-Z])"+  //any letter
                "(?=.*[@#$%^&+=])"+   //at least 1 special character
                "(?=\\S+$)"+  //no white space
                ".{4,}"+  //at least 4 character
                "$";

        if (val.isEmpty()) {
            password.setError("Field can not Empty");
            return false;
        }
        else if (!val.matches(checkpassword)){
            password.setError("password should contain 4 character,no whitespace,1 letter,1 Special character");
            return false;
        }
        else {
            password.setError(null);
            password.setErrorEnabled(false);
            return true;
        }
    }
}
