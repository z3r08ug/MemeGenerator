package com.example.chris.memegenerator.catergory;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.chris.memegenerator.R;
import com.example.chris.memegenerator.view.main.MemeHomeActivity;

import java.util.ArrayList;
import java.util.List;

public class MemeCatergorySelec extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<MemesCategory> memesCategoryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meme_catergory_selec);

        recyclerView = findViewById(R.id.rcCategoryView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        memesCategoryList = new ArrayList<>();
        memesCategoryList.add(new MemesCategory("Angry", false));
        memesCategoryList.add(new MemesCategory("Politics", false));
        memesCategoryList.add(new MemesCategory("Animals", false));
        memesCategoryList.add(new MemesCategory("Reaction", false));
        memesCategoryList.add(new MemesCategory("Feelings", false));
        memesCategoryList.add(new MemesCategory("Romance", false));
        CategoryRCAdapter categoryRCAdapter = new CategoryRCAdapter(memesCategoryList);
        recyclerView.setAdapter(categoryRCAdapter);
    }

    public void homeActivity(View view) {
        Intent intent = new Intent(this, MemeHomeActivity.class);
        startActivity(intent);
    }
}

