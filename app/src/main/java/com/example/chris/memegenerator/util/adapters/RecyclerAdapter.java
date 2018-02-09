package com.example.chris.memegenerator.util.adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.chris.memegenerator.R;
import com.example.chris.memegenerator.util.ImageObj;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by chris on 12/6/2017.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>
{
    private static final String TAG = RecyclerAdapter.class.getSimpleName() + "_TAG";
    List<String> memes = new ArrayList<>();
    List<ImageObj> imageObjList = new ArrayList<>();
    onMemeClickListner memeClickListner;
    public interface onMemeClickListner{
        void onMemeClick(ImageObj imageObj, int position);
        
    }
    public RecyclerAdapter(List<ImageObj> imageObjList, onMemeClickListner memeClickListner) {
        this.imageObjList = imageObjList;
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
        // Glide.with(context).load(imageObjList.get(position).getImageUrl()).into(holder.ivMeme);
        Log.d(TAG, "onBindViewHolder: memes "+ imageObjList.get(position).getImageUrl());
        Glide.with(context).load(imageObjList.get(position).getImageUrl()).into(holder.ivMeme);
        final String postionSiting = Integer.toString(position+1);
        
        holder.tvMemePostion.setText(postionSiting);
holder.bind(imageObjList.get(position),memeClickListner, position);
    }
    
    @Override
    public int getItemCount()
    {
        return imageObjList.size();
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
        public void bind(final ImageObj memeImageObj, final onMemeClickListner listener, final int position) {
            
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onMemeClick(memeImageObj, position);
                }
            });
        }
    }
}
