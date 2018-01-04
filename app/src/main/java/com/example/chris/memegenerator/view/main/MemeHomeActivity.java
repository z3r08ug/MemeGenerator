package com.example.chris.memegenerator.view.main;

import android.app.Activity;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;

import com.example.chris.memegenerator.R;
import com.example.chris.memegenerator.fragments.memesliderfrag.MemeSliderFragment;
import com.example.chris.memegenerator.fragments.searchfragment.SearchMemeFragment;
import com.example.chris.memegenerator.util.MainPagerViewAdapter;

public class MemeHomeActivity extends AppCompatActivity {
    MainPagerViewAdapter mainViewPagerAdapter;
    TabLayout mainTabLayout;
    ViewPager viewPager;
    private Toolbar homeToolbar;
    private Button removeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_meme_activity
        );
        homeToolbar = findViewById(R.id.home_toolbar);
        homeToolbar.setBackgroundColor(Color.parseColor("#19B5FE"));
        viewPager = findViewById(R.id.app_main_pager);
        mainTabLayout = findViewById(R.id.app_main_tabs);
        mainTabLayout.setBackgroundColor(Color.parseColor("#1E8BC3"));
        mainViewPagerAdapter = new MainPagerViewAdapter(getSupportFragmentManager());

        viewPager.setAdapter(mainViewPagerAdapter);
        mainTabLayout.setupWithViewPager(viewPager);
        setSupportActionBar(homeToolbar);
        mainViewPagerAdapter.notifyDataSetChanged();

    }

public void btnRemoveFrag(View view){
    Log.d("great", "btnRemoveFrag: ");
    MemeSliderFragment memeSliderFragment = new MemeSliderFragment();
     getSupportFragmentManager().beginTransaction().remove(memeSliderFragment).commit();
    //fragmentManager.beginTransaction().remove(memeSliderFragment).commit();
}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater =new MenuInflater(this);
menuInflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

//    public void openSearchFragment(View view) {
//        Log.d("FAb", "openSearchFragment: ");
//        SearchMemeFragment searchMemeFragment = new SearchMemeFragment();
//        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//        fragmentTransaction.replace(R.id.searchFragmentFrame, searchMemeFragment).commit();
//    }


}
