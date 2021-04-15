package com.example.fitnessapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.fitnessapp.R;

import java.util.Objects;

public class ViewPagerAdapter extends PagerAdapter {
    private Context context;
    private int[] images;
    LayoutInflater mLayoutInflater;

    public ViewPagerAdapter(Context context, int[] images) {
        this.context = context;
        this.images = images;
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == ((LinearLayout) object);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View itemView = mLayoutInflater.inflate(R.layout.item_workout, container, false);
        /*
        ImageView imageView = itemView.findViewById(R.id.imageViewMain);
        imageView.setImageResource(images[position]);
        imageView.setAlpha((float) 0.1);
        imageView.setScaleType(ImageView.ScaleType.CENTER);
        Objects.requireNonNull(container).addView(itemView);
         */
        return itemView;
    }
}
