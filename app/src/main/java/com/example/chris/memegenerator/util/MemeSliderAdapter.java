package com.example.chris.memegenerator.util;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.example.chris.memegenerator.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by  Admin on 1/2/2018.
 */

public class MemeSliderAdapter extends PagerAdapter {
  //  int[] imageIds ={R.drawable.meme1,R.drawable.meme2,R.drawable.meme3,R.drawable.meme4,R.drawable.meme5};
    List<Image> imageList=new ArrayList<>();
    LayoutInflater layoutInflater;
    Context context;

    public MemeSliderAdapter(Context context, List<Image> images) {
        this.context = context;
        this.imageList= images;
    }

    @Override
    public int getCount() {
        return imageList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == (LinearLayout)object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = layoutInflater.inflate(R.layout.memeslider, container, false);
        ImageView memeSlider=view.findViewById(R.id.ivMemeSlider);
        String imageUrl = imageList.get(position).getImageUrl();
        Log.d("Check", "instantiateItem: "+imageUrl);
       // memeSlider.setImageResource(imageList.get(position).getImageUrl());
        Glide.with(context).load(imageUrl).into(memeSlider);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
       container.removeView((LinearLayout)object);
    }
}
