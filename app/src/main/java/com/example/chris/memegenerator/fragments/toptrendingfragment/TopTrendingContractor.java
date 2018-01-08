package com.example.chris.memegenerator.fragments.toptrendingfragment;

import com.example.chris.memegenerator.util.BasePresenter;
import com.example.chris.memegenerator.util.BaseView;

import java.util.List;

/**
 * Created by  Admin on 1/7/2018.
 */

public interface TopTrendingContractor {

    interface View extends BaseView{
        void setTopTrendingMemes(List<String> memesList);
        void showProgress(String progress);

    }
    interface Presenter extends BasePresenter<View>{
        void getTopTrendingMemes(String searchTerm);
    }
}
