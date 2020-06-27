package com.alpha.city_guide.HelperClasses.HomeAdapter;

public class FeaturedHelperClass {

    int image;
    String heading,description;

    public FeaturedHelperClass(int image, String heading, String description) {
        this.image = image;
        this.heading = heading;
        this.description = description;
    }

    public int getImage() {
        return image;
    }

    public String getHeading() {
        return heading;
    }

    public String getDescription() {
        return description;
    }
}
