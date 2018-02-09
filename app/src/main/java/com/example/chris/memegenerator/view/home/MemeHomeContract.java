package com.example.chris.memegenerator.view.home;


import com.example.chris.memegenerator.model.pojo.bingsearch.BingSearch;
import com.example.chris.memegenerator.model.pojo.keywordfinder.Keywords;
import com.example.chris.memegenerator.util.base.BasePresenter;
import com.example.chris.memegenerator.util.base.BaseView;

import java.util.List;

/**
 * Created by Admin on 11/29/2017.
 */

public interface MemeHomeContract
{
    //methods for main activity
    interface View extends BaseView
    {
        void setKeywords(Keywords keywords);
        void setBingResponseTrending(List<String> memes);
        void setBingResponseInterests(List<String> memes);
        void showProgress(String progress);
    }

    interface Presenter extends BasePresenter<View>
    {
        void getKeywords(String status);
        void getBingResponseTrending(String search);
        void getBingResponseInterests(String search);
    }
}
