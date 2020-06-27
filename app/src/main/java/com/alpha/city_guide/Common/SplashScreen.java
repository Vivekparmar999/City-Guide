package com.alpha.city_guide.Common;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.alpha.city_guide.R;
import com.alpha.city_guide.User.UserDashBoard;

import androidx.appcompat.app.AppCompatActivity;


public class SplashScreen extends AppCompatActivity {

    private static int SPLASH_TIME = 5000;

    ImageView img_splash;
    TextView powerByLIne;
    Animation sideAnim, bottomAnim;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splash_screen);

        img_splash = findViewById(R.id.img_splash);
        powerByLIne = findViewById(R.id.powerByLine);
        sideAnim = AnimationUtils.loadAnimation(this, R.anim.side_anim);
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_anim);


        img_splash.setAnimation(sideAnim);
        powerByLIne.setAnimation(bottomAnim);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                sharedPreferences = getSharedPreferences("onBoardingScreen", MODE_PRIVATE);

                boolean isFirstTime=sharedPreferences.getBoolean("firstTime",true);

                if(isFirstTime){

                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putBoolean("firstTime",false);
                    editor.commit();

                    Intent intent = new Intent(getApplicationContext(), OnBoarding.class);
                    startActivity(intent);
                    finish();
                }else{
                    Intent intent = new Intent(getApplicationContext(), UserDashBoard.class);
                    startActivity(intent);
                    finish();
                }

            }
        }, SPLASH_TIME);
    }
}
