package com.alpha.city_guide.HelperClasses.HomeAdapter;

import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.alpha.city_guide.R;
import java.util.ArrayList;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FeaturedAdapter extends RecyclerView.Adapter<FeaturedAdapter.FeatureViewHolder> {


    ArrayList<FeaturedHelperClass> featuredLocations;


    //HOLD DATA
    public FeaturedAdapter(ArrayList<FeaturedHelperClass> featuredLocations) {
        this.featuredLocations = featuredLocations;
    }

    @NonNull
    @Override
    public FeatureViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.featured_card_view,parent,false);
        FeatureViewHolder featureViewHolder=new FeatureViewHolder(view);
        return featureViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FeatureViewHolder holder, int position) {

        GradientDrawable gradientDrawable1 =new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT,new int[]{0xffeff400,0xffaff600});


        FeaturedHelperClass featuredHelperClass=featuredLocations.get(position);

        holder.image.setImageResource(featuredHelperClass.getImage());
        holder.desc.setText(featuredHelperClass.getDescription());
        holder.headings.setText(featuredHelperClass.getHeading());
        holder.desc.setBackground(gradientDrawable1);
    }

    @Override
    public int getItemCount() {
        return featuredLocations.size();
    }


    //Hold Design
    public static class FeatureViewHolder extends RecyclerView.ViewHolder{

        private ImageView image;
        private TextView headings,desc;

      public FeatureViewHolder(View itemView){
            super(itemView);
            image=itemView.findViewById(R.id.featured_image);
            headings=itemView.findViewById(R.id.featured_title);
            desc=itemView.findViewById(R.id.featured_desc);
        }

    }
}
