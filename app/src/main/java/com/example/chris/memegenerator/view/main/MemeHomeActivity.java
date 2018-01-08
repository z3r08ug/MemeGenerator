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

import com.example.chris.memegenerator.MemeApplication;
import com.example.chris.memegenerator.R;
import com.example.chris.memegenerator.fragments.interestfragment.TrendingInterestFragment;
import com.example.chris.memegenerator.fragments.memesliderfrag.MemeSliderFragment;
import com.example.chris.memegenerator.fragments.searchfragment.SearchMemeFragment;
import com.example.chris.memegenerator.fragments.toptrendingfragment.TrendingFragment;
import com.example.chris.memegenerator.util.Constants;
import com.example.chris.memegenerator.util.MainPagerViewAdapter;

import java.util.List;

import javax.inject.Inject;

public class MemeHomeActivity extends AppCompatActivity implements MainContract.View{
    @Inject
    MainPresenter presenter;
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
        MemeApplication.get(this).getMainComponent().inject(this);
        homeToolbar = findViewById(R.id.home_toolbar);
        homeToolbar.setBackgroundColor(Color.parseColor("#19B5FE"));
        viewPager = findViewById(R.id.app_main_pager);
        mainTabLayout = findViewById(R.id.app_main_tabs);
        mainTabLayout.setBackgroundColor(Color.parseColor("#1E8BC3"));
        mainViewPagerAdapter = new MainPagerViewAdapter(getSupportFragmentManager());
        presenter.attachView(this);
        presenter.getBingSearch("memes",Constants.topTrending);
        presenter.getBingSearch("food",Constants.interestTrending);
        presenter.getBingSearch("soccer",Constants.interestTrending);
        viewPager.setAdapter(mainViewPagerAdapter);
        mainTabLayout.setupWithViewPager(viewPager);
        setSupportActionBar(homeToolbar);
        mainViewPagerAdapter.notifyDataSetChanged();
        Constants.setallFALSE();

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

    @Override
    public void showError(String error) {

    }

    @Override
    public void setBingSearch(List<String> memes) {
        Log.d("Set", "setBingSearch: "+memes.size());
            TrendingFragment trendingFragment = TrendingFragment.newInstance(memes);

    }

    @Override
    public void setInterestBingSearch(List<String> memes) {
        TrendingInterestFragment trendingInterestFragment = TrendingInterestFragment.newInstance(memes);
    }

    @Override
    public void setTopTrending() {

    }

    @Override
    public void setInterestTrending() {

    }

    @Override
    public void showProgress(String progress) {

    }

//    public void openSearchFragment(View view) {
//        Log.d("FAb", "openSearchFragment: ");
//        SearchMemeFragment searchMemeFragment = new SearchMemeFragment();
//        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//        fragmentTransaction.replace(R.id.searchFragmentFrame, searchMemeFragment).commit();
//    }


}
