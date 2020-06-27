package com.alpha.city_guide.Common.LoginSignUp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;

import com.alpha.city_guide.R;

public class RetailerStartUpScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_retailer_start_up_screen);

    }

    public void LoginScreen(View view) {
        Intent intent =new Intent(getApplicationContext(), LoginSelection.class);

        Pair[] pairs = new Pair[1];
        pairs[0] = new Pair<View,String>(findViewById(R.id.login_btn),"transition_login");
        ActivityOptions options =ActivityOptions.makeSceneTransitionAnimation(RetailerStartUpScreen.this,pairs);
        startActivity(intent,options.toBundle());
    }

    public void SignUpScreen(View view) {
        Intent intent =new Intent(getApplicationContext(),SignUp.class);

        Pair[] pairs = new Pair[1];
        pairs[0] = new Pair<View,String>(findViewById(R.id.signup_btn),"transition_signup");
        ActivityOptions options =ActivityOptions.makeSceneTransitionAnimation(RetailerStartUpScreen.this,pairs);
        startActivity(intent,options.toBundle());
    }
}
