package com.example.chris.memegenerator.fragments.toptrendingfragment;

import java.util.List;

/**
 * Created by  Admin on 1/7/2018.
 */

public class TopTrendingPresenter implements TopTrendingContractor.Presenter {
    TopTrendingContractor.View view;
    @Override
    public void attachView(TopTrendingContractor.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
this.view = null;
    }

    @Override
    public void getTopTrendingMemes(List<String> memesList) {



    }
}
