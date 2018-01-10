package com.example.chris.memegenerator.view;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.chris.memegenerator.R;
import com.example.chris.memegenerator.util.FavoritesHandler;
import com.example.chris.memegenerator.util.RecyclerAdapter2;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class FavoriteMemesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_memes);

        Set<String> Fav = FavoritesHandler.getFavorites(this);
        String [] dummy = Fav.toArray(new String[Fav.size()]);
        List<String> Favlist = new ArrayList<>();
        for (int i = 0; i <dummy.length ; i++) {
            Favlist.add(dummy[i]);

        }
        RecyclerView recyclerView = findViewById(R.id.mainlayoutRecycleview);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(FavoriteMemesActivity.this, 2, LinearLayoutManager.VERTICAL, false);
        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(itemAnimator);
        RecyclerAdapter2 recyclerAdapter2 = new RecyclerAdapter2(Favlist);
        recyclerView.setAdapter(recyclerAdapter2);

    }
}
