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

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MemeHomeActivity extends AppCompatActivity implements MainContract.View{
    private static final String TAG = MemeHomeActivity.class.getSimpleName() + "_TAG";
    @Inject
    MainPresenter presenter;
    MainPagerViewAdapter mainViewPagerAdapter;
    TabLayout mainTabLayout;
    ViewPager viewPager;
    private Toolbar homeToolbar;
    List<String> interests;
    List<List<String>> interestsMemes;
    
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
//        presenter.getBingSearch("trendingMemes", Constants.topTrending);
        presenter.getBingSearch("cat trendingMemes", Constants.interestTrending);
        viewPager.setAdapter(mainViewPagerAdapter);
        mainTabLayout.setupWithViewPager(viewPager);
        setSupportActionBar(homeToolbar);
        mainViewPagerAdapter.notifyDataSetChanged();
        
        interests = new ArrayList<>();
        interestsMemes = new ArrayList<>();
    
        try
        {
            InputStream instream = openFileInput("interests.txt");
            if (instream != null)
            {
                InputStreamReader inputreader = new InputStreamReader(instream);
                BufferedReader buffreader = new BufferedReader(inputreader);
                String line,line1 = "";
                try
                {
                    while ((line = buffreader.readLine()) != null)
                        line1+=line;
                    Log.d(TAG, "onCreate: "+line1);
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
        catch (Exception e)
        {
            Log.d(TAG, "onCreate: "+e.toString());
        }
        
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
    public void setInterestBingSearch(List<String> memes)
    {
        Log.d("setInterests", "setInterestBingSearch: "+memes.size());
        TrendingInterestFragment trendingInterestFragment = TrendingInterestFragment.newInstance(memes);
    }
    
    @Override
    public void setTopTrending() {
    
    }
    
    @Override
    public void setInterestTrending()
    {
    
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
