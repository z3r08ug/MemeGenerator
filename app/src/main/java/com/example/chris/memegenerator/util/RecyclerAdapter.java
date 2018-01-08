package com.example.chris.memegenerator.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.chris.memegenerator.R;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by chris on 12/6/2017.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>
{
    private static final String TAG = RecyclerAdapter.class.getSimpleName() + "_TAG";
    List<String> memes = new ArrayList<>();
List<Image> imageList = new ArrayList<>();
onMemeClickListner memeClickListner;
    public interface onMemeClickListner{
        void onMemeClick(Image image);

    }
    public RecyclerAdapter(List<Image> imageList, onMemeClickListner memeClickListner) {
        this.imageList = imageList;
        this.memeClickListner = memeClickListner;
    }

    Context context;
    private SharedPreferences sharedPreferences;

//    public RecyclerAdapter(List<String> memes)
//    {
//        this.memes = memes;
//
//    }
    
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, null);
        context = parent.getContext();
        sharedPreferences = context.getSharedPreferences("settings", Context.MODE_PRIVATE);
        return new ViewHolder(view);
    }
    
    @Override
    public void onBindViewHolder(RecyclerAdapter.ViewHolder holder, final int position)
    {
       // Glide.with(context).load(imageList.get(position).getImageUrl()).into(holder.ivMeme);
        Log.d(TAG, "onBindViewHolder: memes "+ memes.get(position));
        Glide.with(context).load(memes.get(position)).into(holder.ivMeme);
        final String postionSiting = Integer.toString(position+1);

        holder.tvMemePostion.setText(postionSiting);
//holder.bind(imageList.get(position), memeClickListner);
//        holder.ivMeme.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                MemeSliderFragment searchMemeFragment = MemeSliderFragment.newInstance(memes);
//////                FragmentManager fragmentManager = ((Activity)context).getFragmentManager();
//////                fragmentManager.beginTransaction().replace(R.id.searchFragmentFrame,searchMemeFragment).commit();
////       FragmentTransaction fragmentTransaction = ((MemeHomeActivity)context).getSupportFragmentManager().beginTransaction();;
//////                FragmentTransaction       fragmentTransaction =
////                fragmentTransaction.replace(R.id.searchFragmentFrame, searchMemeFragment).commit();
//            }
//        });

    }

    @Override
    public int getItemCount()
    {
        return memes.size();
    }
    
    public class ViewHolder extends RecyclerView.ViewHolder
    {
        private final ImageView ivMeme;
        private final TextView tvMemePostion;

        public ViewHolder(View itemView)
        {
            super(itemView);
            ivMeme = itemView.findViewById(R.id.ivMeme);
            tvMemePostion = itemView.findViewById(R.id.tvImageCount);
        }
        public void bind(final Image memeImage, final onMemeClickListner listener) {

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                   listener.onMemeClick(memeImage);
                }
            });
        }
    }
}
