package com.alpha.city_guide.Common.LoginSignUp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.alpha.city_guide.R;

import java.util.Calendar;

public class Signup2nd_class extends AppCompatActivity {

    ImageView backbtn;
    Button next, login;
    TextView titleText;
    RadioGroup radioGroup;
    RadioButton selectedGender;
    DatePicker datePicker;
    String fullnameI,emailI,usernameI,passwordI,dateI,genderI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup2nd_class);

        //animation Hooks
        backbtn = findViewById(R.id.Signup_back_button);
        next = findViewById(R.id.signup_next_btn);
        login = findViewById(R.id.signup_login_btn);
        titleText = findViewById(R.id.signup_title_text);
        //hooks
        radioGroup=findViewById(R.id.radio_group);
        datePicker=findViewById(R.id.age_picker);


        Intent intent=getIntent();
         fullnameI=intent.getStringExtra("fullnameI");
         emailI=intent.getStringExtra("emailI");
         usernameI=intent.getStringExtra("usernameI");
         passwordI=intent.getStringExtra("passwordI");
    }

    public void callNextSignupScreen(View view) {

        if(!validateGender() | !validateAge()){
            return;
        }
        //hooks
        selectedGender=findViewById(radioGroup.getCheckedRadioButtonId());
        genderI = selectedGender.getText().toString();

        int dayI=datePicker.getDayOfMonth();
        int monthI=datePicker.getMonth();
        int yearI=datePicker.getYear();
        dateI = dayI+"/"+monthI+"/"+yearI;


        Intent intent=new Intent(getApplicationContext(),SignUp3rdClass.class);

        intent.putExtra("fullnameI",fullnameI);
        intent.putExtra("emailI",emailI);
        intent.putExtra("usernameI",usernameI);
        intent.putExtra("passwordI",passwordI);
        intent.putExtra("genderI",genderI);
        intent.putExtra("dateI",dateI);

        Pair[] pairs = new Pair[4];
        pairs[0] = new Pair<View,String>(backbtn,"transition_back_btn");
        pairs[1] = new Pair<View,String>(next,"callNextSignupScreen");
        pairs[2] = new Pair<View,String>(login,"callLoginSignUpScreen");
        pairs[3] = new Pair<View,String>(titleText,"transition_title_btn");

        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Signup2nd_class.this,pairs);
        startActivity(intent,options.toBundle());
    }

    public void callLoginSignUpScreen(View view) {
    }


    private boolean validateGender(){
        if(radioGroup.getCheckedRadioButtonId()==-1){
            Toast.makeText(this,"Please Select Gender",Toast.LENGTH_SHORT).show();
            return  false;
        }else {
            return  true;
        }
    }

    private boolean validateAge(){
        int currentYear= Calendar.getInstance().get(Calendar.YEAR);
        int userAge = datePicker.getYear();
        int isAgeValid =currentYear - userAge;

        if(isAgeValid < 14){
            Toast.makeText(this,"Your are not allow to enter",Toast.LENGTH_SHORT).show();
            return false;
        }else {
            return  true;
        }
    }
}
