package com.alpha.city_guide.HelperClasses;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alpha.city_guide.R;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

public class SliderAdapter extends PagerAdapter {


    Context context;
    LayoutInflater layoutInflater;

    public SliderAdapter(Context context) {
        this.context = context;
    }

    int images[] = {
            R.drawable.search_place,
            R.drawable.make_a_call,
            R.drawable.add_missing_place,
            R.drawable.sit_back_and_relax,
    };


    String headings[] = {
            "LoremIpsulm lorem1",
            "LoremIpsulm lorem2",
            "LoremIpsulm lorem3",
            "LoremIpsulm lorem4",
    };

    String footers[] = {
            "annnncaklandksla akks annnncaklandksla akks annnncaklandksla akks 1",
            "annnncaklandksla akks annnncaklandksla akks annnncaklandksla akks 2",
            "annnncaklandksla akks annnncaklandksla akks annnncaklandksla akks 3",
            "annnncaklandksla akks annnncaklandksla akks annnncaklandksla akks 4",
    };

    @Override
    public int getCount() {
        return headings.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (ConstraintLayout) object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v =layoutInflater.inflate(R.layout.slides_layout,container,false);

        ImageView imageView=v.findViewById(R.id.sliding_image);
        TextView heading=v.findViewById(R.id.slider_heading);
        TextView footer=v.findViewById(R.id.slider_footer);

        imageView.setImageResource(images[position]);
        heading.setText(headings[position]);
        footer.setText(footers[position]);

        container.addView(v);
        return v;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ConstraintLayout)object);
    }
}
