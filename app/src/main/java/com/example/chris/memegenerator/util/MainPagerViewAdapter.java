package com.example.chris.memegenerator.util;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;

import com.example.chris.memegenerator.fragments.searchfragment.SearchMemeFragment;
import com.example.chris.memegenerator.fragments.interestfragment.TrendingInterestFragment;
import com.example.chris.memegenerator.fragments.toptrendingfragment.TrendingFragment;

import java.util.logging.Handler;

/**
 * Created by  Admin on 12/22/2017.
 */

public class MainPagerViewAdapter extends FragmentStatePagerAdapter {

    private TrendingFragment trendingFragment;

    public MainPagerViewAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:

                        trendingFragment = new TrendingFragment();


                return trendingFragment;


            case 1:
                TrendingInterestFragment trendingInterestFragment = new TrendingInterestFragment();
                return trendingInterestFragment;
           case 2:
               SearchMemeFragment searchMemeFcragment = new SearchMemeFragment();

                return searchMemeFcragment;
                default:
                    return null;
        }

    }

    @Override
    public int getCount() {
        return 3;
    }
    public CharSequence getPageTitle(int position){
        switch (position) {
            case 0:
                return "Top Trending";
            case 1:
                return "Trending Interest";
            case 2:
                return "Search";

            default:
                return null;
        }
    }

    @Override
    public int getItemPosition(Object object) {
        return PagerAdapter.POSITION_NONE;
    }
}
