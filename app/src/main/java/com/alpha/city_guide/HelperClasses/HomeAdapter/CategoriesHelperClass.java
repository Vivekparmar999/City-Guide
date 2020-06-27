package com.alpha.city_guide.HelperClasses.HomeAdapter;


import android.graphics.drawable.GradientDrawable;

public class CategoriesHelperClass {

    GradientDrawable background_gradient;
    int categories_image;
    String categories_title;

    public CategoriesHelperClass(GradientDrawable background_gradient, int categories_image, String categories_title) {
        this.background_gradient = background_gradient;
        this.categories_image = categories_image;
        this.categories_title = categories_title;
    }

    public GradientDrawable getBackground_gradient() {
        return background_gradient;
    }

    public int getCategories_image() {
        return categories_image;
    }

    public String getCategories_title() {
        return categories_title;
    }
}
