package com.alpha.city_guide.HelperClasses.HomeAdapter;

public class MostViewedHelperClass {
    int mv_image;
    String mv_title;

    public MostViewedHelperClass(int mv_image, String mv_title) {
        this.mv_image = mv_image;
        this.mv_title = mv_title;
    }

    public int getMv_image() {
        return mv_image;
    }

    public String getMv_title() {
        return mv_title;
    }
}
