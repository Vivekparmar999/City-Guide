package com.alpha.city_guide.Common;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alpha.city_guide.HelperClasses.SliderAdapter;
import com.alpha.city_guide.R;
import com.alpha.city_guide.User.UserDashBoard;

public class OnBoarding extends AppCompatActivity {

    ViewPager viewPager;
    LinearLayout linearLayout;
    TextView[] dots;
    SliderAdapter sliderAdapter;
    Button letsgetStarted;
    Animation animation;
    int currentPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_on_boarding);

        letsgetStarted=findViewById(R.id.get_started_btn);
        viewPager=findViewById(R.id.viewPager);
        linearLayout=findViewById(R.id.linearlayout);
        sliderAdapter =new SliderAdapter(this);
        viewPager.setAdapter(sliderAdapter);
        addDots(0);
        viewPager.addOnPageChangeListener(changeListener);
    }

    private  void addDots(int position){
        dots=new TextView[4];
        linearLayout.removeAllViews();
        for(int i=0;i<dots.length;i++){
            dots[i]=new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226"));
            dots[i].setTextSize(35);
            linearLayout.addView(dots[i]);
        }

        if(dots.length>0){
            dots[position].setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        }

    }

    ViewPager.OnPageChangeListener changeListener =new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDots(position);
            currentPosition=position;

            if(position==0){letsgetStarted.setVisibility(View.INVISIBLE);}
            else if(position==1){letsgetStarted.setVisibility(View.INVISIBLE);}
            else if(position == 2){letsgetStarted.setVisibility(View.INVISIBLE);}
            else{
                animation= AnimationUtils.loadAnimation(OnBoarding.this,R.anim.bottom_anim);
                letsgetStarted.setAnimation(animation);
                letsgetStarted.setVisibility(View.VISIBLE);}
        }



        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    public void skip(View view){
        startActivity(new Intent(this, UserDashBoard.class));
        viewPager.setCurrentItem(currentPosition+1);
        finish();
    }

    public void next(View view){
        viewPager.setCurrentItem(currentPosition + 1);
    }
}