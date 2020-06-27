package com.alpha.city_guide.User;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.alpha.city_guide.Common.LoginSignUp.RetailerStartUpScreen;
import com.alpha.city_guide.HelperClasses.HomeAdapter.CategoriesAdapter;
import com.alpha.city_guide.HelperClasses.HomeAdapter.CategoriesHelperClass;
import com.alpha.city_guide.HelperClasses.HomeAdapter.FeaturedAdapter;
import com.alpha.city_guide.HelperClasses.HomeAdapter.FeaturedHelperClass;
import com.alpha.city_guide.HelperClasses.HomeAdapter.MostViewedAdpater;
import com.alpha.city_guide.HelperClasses.HomeAdapter.MostViewedHelperClass;
import com.alpha.city_guide.R;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class UserDashBoard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    RecyclerView recyclerView, mostViewedRecycler, categoriesRecycler;
    RecyclerView.Adapter adapter;
    GradientDrawable gradient1, gradient2, gradient3, gradient4;
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    static  final  float END_SCALE =0.7f;
    LinearLayout contentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_user_dash_board);



        //Hooks
        mostViewedRecycler = findViewById(R.id.most_viewed_recycler);
        categoriesRecycler = findViewById(R.id.categories_recycler);
        recyclerView = findViewById(R.id.feature_recycleview);
        contentView=findViewById(R.id.content);

        //menu Hooks
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);

        //Navigation Drawer
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);

        //Functions will be executed automatically when this activity will be created
        featureRecycleView();
        mostViewedRecycler();
        categoriesRecycler();

        animateNavigator();

    }

    private void animateNavigator() {

        drawerLayout.setScrimColor(getResources().getColor(R.color.colorPrimary));
        drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                final float diffScaleOffset=slideOffset*(1-END_SCALE);
                final float offsetScale = 1-diffScaleOffset;
                contentView.setScaleX(offsetScale);
                contentView.setScaleY(offsetScale);


                //Translate
                final float xOffSet = drawerView.getWidth()*slideOffset;
                final float xOffsetDiff = contentView.getWidth()*diffScaleOffset/2;
                final float xTranslation = xOffSet-xOffsetDiff;
                contentView.setTranslationX(xTranslation);
            }
        });
    }

    private void featureRecycleView() {

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        ArrayList<FeaturedHelperClass> featuredLocations = new ArrayList<>();

        featuredLocations.add(new FeaturedHelperClass(R.drawable.add_missing_place, "MC Donalds", "Dummy DummyDummyDummyDummyDummyDummy"));
        featuredLocations.add(new FeaturedHelperClass(R.drawable.add_missing_place, "MV Donalds", "Dummy DummyDummyDummyDummyDummyDummy"));
        featuredLocations.add(new FeaturedHelperClass(R.drawable.add_missing_place, "MO Donalds", "Dummy DummyDummyDummyDummyDummyDummy"));

        adapter = new FeaturedAdapter(featuredLocations);
        recyclerView.setAdapter(adapter);


    }

    private void mostViewedRecycler() {

        mostViewedRecycler.setHasFixedSize(true);
        mostViewedRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        ArrayList<MostViewedHelperClass> mostViewedLocations = new ArrayList<>();
        mostViewedLocations.add(new MostViewedHelperClass(R.drawable.add_missing_place, "McDonald's"));
        mostViewedLocations.add(new MostViewedHelperClass(R.drawable.add_missing_place, "Edenrobe"));
        mostViewedLocations.add(new MostViewedHelperClass(R.drawable.add_missing_place, "J."));
        mostViewedLocations.add(new MostViewedHelperClass(R.drawable.add_missing_place, "Walmart"));

        adapter = new MostViewedAdpater(mostViewedLocations);
        mostViewedRecycler.setAdapter(adapter);

    }


    private void categoriesRecycler() {

        //All Gradients
        gradient2 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xffd4cbe5, 0xffd4cbe5});
        gradient1 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xff7adccf, 0xff7adccf});
        gradient3 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xfff7c59f, 0xFFf7c59f});
        gradient4 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xffb8d7f5, 0xffb8d7f5});


        ArrayList<CategoriesHelperClass> categoriesHelperClasses = new ArrayList<>();
        categoriesHelperClasses.add(new CategoriesHelperClass(gradient1, R.drawable.add_missing_place, "Education"));
        categoriesHelperClasses.add(new CategoriesHelperClass(gradient2, R.drawable.add_missing_place, "HOSPITAL"));
        categoriesHelperClasses.add(new CategoriesHelperClass(gradient3, R.drawable.add_missing_place, "Restaurant"));
        categoriesHelperClasses.add(new CategoriesHelperClass(gradient4, R.drawable.add_missing_place, "Shopping"));
        categoriesHelperClasses.add(new CategoriesHelperClass(gradient1, R.drawable.add_missing_place, "Transport"));


        categoriesRecycler.setHasFixedSize(true);
        adapter = new CategoriesAdapter(categoriesHelperClasses);
        categoriesRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        categoriesRecycler.setAdapter(adapter);

    }

    //Navigation function
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.nav_all_category:
                startActivity(new Intent(getApplicationContext(),AllCategories.class));
                break;
        }
        return true;
    }

    @Override
    public void onBackPressed() {

        if(drawerLayout.isDrawerVisible(GravityCompat.START))
            drawerLayout.closeDrawer(GravityCompat.START);
        else super.onBackPressed();
    }

    //menu Icon click
    public void menuIcon(View view) {
        if (drawerLayout.isDrawerVisible(GravityCompat.START))
            drawerLayout.closeDrawer(GravityCompat.END);
        else drawerLayout.openDrawer(GravityCompat.START);
    }

    public void callRetailerScreen(View view) {
        startActivity(new Intent(getApplicationContext(), RetailerStartUpScreen.class));
    }
}
