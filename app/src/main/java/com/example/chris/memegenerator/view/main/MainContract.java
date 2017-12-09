package com.example.chris.memegenerator.view.main;


import com.example.chris.memegenerator.util.BasePresenter;
import com.example.chris.memegenerator.util.BaseView;

/**
 * Created by Admin on 11/29/2017.
 */

public interface MainContract
{
    //methods for main activity
    interface View extends BaseView
    {
        void setTopTrending();
        void setInterestTrending();
//        void setTopTrending(TopTrendingResponse topTrending);
//        void setInterestTrending(InterestTrendingResponse interestTrending);
        void showProgress(String progress);
    }

    interface Presenter extends BasePresenter<View>
    {
        void getTopTrending();
        void getInterestTrending();
    }
}
