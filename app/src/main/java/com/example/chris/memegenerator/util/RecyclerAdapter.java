package com.example.chris.memegenerator.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

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
    Context context;
    private SharedPreferences sharedPreferences;
    
    public RecyclerAdapter(List<String> memes)
    {
        this.memes = memes;
        
    }
    
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
    public void onBindViewHolder(RecyclerAdapter.ViewHolder holder, int position)
    {
        Glide.with(context).load(memes.get(position)).into(holder.ivMeme);
    }
    
    @Override
    public int getItemCount()
    {
        return memes.size();
    }
    
    public class ViewHolder extends RecyclerView.ViewHolder
    {
        private final ImageView ivMeme;
        public ViewHolder(View itemView)
        {
            super(itemView);
            ivMeme = itemView.findViewById(R.id.ivMeme);
        }
    }
}
