package com.example.chris.memegenerator.view;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.chris.memegenerator.R;
import com.example.chris.memegenerator.fragments.memesliderfrag.MemeSliderFragment;
import com.example.chris.memegenerator.util.FavoritesHandler;
import com.example.chris.memegenerator.util.Image;
import com.example.chris.memegenerator.util.RecyclerAdapter;
import com.example.chris.memegenerator.util.RecyclerAdapter2;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class FavoriteMemesActivity extends AppCompatActivity {

    private View view;
    private Set<String> fav;
    private String[] dummy;
    private List<Image> favlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_memes);

        fav = FavoritesHandler.getFavorites(this);
        dummy = fav.toArray(new String[fav.size()]);

        //view.setVisibility(View.INVISIBLE);
        favlist = new ArrayList<>();
        for (int i = 0; i < dummy.length ; i++) {
            favlist.add(new Image(dummy[i]));

        }
        RecyclerView recyclerView = findViewById(R.id.mainlayoutRecycleview);
//                                RecyclerView.LayoutManager layoutManager = new GridLayoutManager(Main2Activity.this, 2, LinearLayoutManager.VERTICAL, false);
//                                RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
//                                recyclerView.setLayoutManager(layoutManager);
//                                recyclerView.setItemAnimator(itemAnimator);
//                                RecyclerAdapter2 recyclerAdapter2 = new RecyclerAdapter2(posturl);
//                                recyclerView.setAdapter(recyclerAdapter2);
        recyclerView.setLayoutManager(new GridLayoutManager(FavoriteMemesActivity.this, 2));
        recyclerView.setAdapter(new RecyclerAdapter(favlist, new RecyclerAdapter.onMemeClickListner() {
            @Override
            public void onMemeClick(Image image, int position) {
                MemeSliderFragment memeSliderFragment = MemeSliderFragment.newInstance(favlist, position);
                //FragmentManager fragmentManager= getFragmentManager();
                //fragmentManager.beginTransaction().replace(R.id.searchFragmentFrame,memeSliderFragment).addToBackStack("Slider").commit();
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.searchFragmentFrame, memeSliderFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        }));

    }
}
