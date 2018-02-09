package com.example.chris.memegenerator.view.favorites;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.chris.memegenerator.R;
import com.example.chris.memegenerator.fragments.memesliderfrag.MemeSliderFragment;
import com.example.chris.memegenerator.util.ImageObj;
import com.example.chris.memegenerator.util.handlers.FavoritesHandler;
import com.example.chris.memegenerator.util.GridSpacingItemDecoration;
import com.example.chris.memegenerator.util.adapters.RecyclerAdapter;
import com.example.chris.memegenerator.view.home.MemeHomeActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class FavoriteMemesActivity extends AppCompatActivity {

    private View view;
    private Set<String> fav;
    private String[] dummy;
    private List<ImageObj> favlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_memes);

        fav = FavoritesHandler.getFavorites(this);
        dummy = fav.toArray(new String[fav.size()]);

        favlist = new ArrayList<>();
        for (int i = 0; i < dummy.length ; i++) {
            favlist.add(new ImageObj(dummy[i]));

        }
        RecyclerView recyclerView = findViewById(R.id.mainlayoutRecycleview);
        recyclerView.setLayoutManager(new GridLayoutManager(FavoriteMemesActivity.this, 2));
        int spanCount = 2; // 3 columns
        int spacing = 50; // 50px
        boolean includeEdge = false;
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, includeEdge));
        recyclerView.setAdapter(new RecyclerAdapter(favlist, new RecyclerAdapter.onMemeClickListner() {
            @Override
            public void onMemeClick(ImageObj imageObj, int position) {
                MemeSliderFragment memeSliderFragment = MemeSliderFragment.newInstance(favlist, position);
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.searchFragmentFrame, memeSliderFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        }));

    }
    
    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        startActivity(new Intent(this, MemeHomeActivity.class));
    }
}
