package com.example.chris.memegenerator.view.createMeme;

import com.example.chris.memegenerator.util.base.BasePresenter;
import com.example.chris.memegenerator.util.base.BaseView;

import java.util.List;

/**
 * Created by chris on 12/11/2017.
 */

public interface CreateMemeContract
{
    interface View extends BaseView
    {
        void setBingSearch(List<String> memes);
        void showProgress(String progress);
    }
    
    interface Presenter extends BasePresenter<View>
    {
        void getBingSearch(String search, String whichCall);
    }
}
