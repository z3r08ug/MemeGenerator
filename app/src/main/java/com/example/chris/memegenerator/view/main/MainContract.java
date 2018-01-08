package com.example.chris.memegenerator.view.main;


import com.example.chris.memegenerator.util.BasePresenter;
import com.example.chris.memegenerator.util.BaseView;
import com.example.chris.memegenerator.util.pojo.bingsearch.BingSearch;
import com.facebook.login.widget.LoginButton;

import java.util.List;

/**
 * Created by Admin on 11/29/2017.
 */

public interface MainContract
{
    //methods for main activity
    interface View extends BaseView
    {
        void setBingSearch(List<String> memes);
        void setTopTrending();
        void setInterestTrending();
//        void setTopTrending(TopTrendingResponse topTrending);
//        void setInterestTrending(InterestTrendingResponse interestTrending);
        void showProgress(String progress);
    }

    interface Presenter extends BasePresenter<View>
    {
        void getBingSearch(String search);
        void getInterestTrending();
    }
}
