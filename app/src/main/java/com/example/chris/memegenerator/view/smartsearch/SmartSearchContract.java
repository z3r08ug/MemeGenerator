package com.example.chris.memegenerator.view.smartsearch;

import com.example.chris.memegenerator.model.pojo.keywordfinder.Keywords;
import com.example.chris.memegenerator.util.base.BasePresenter;
import com.example.chris.memegenerator.util.base.BaseView;
import com.example.chris.memegenerator.view.home.MemeHomeContract;

import java.util.List;

/**
 * Created by chris on 2/8/2018.
 */

public interface SmartSearchContract
{
    //methods for main activity
    interface View extends BaseView
    {
        void setBingResponse(List<String> memes);
        void showProgress(String progress);
    }
    
    interface Presenter extends BasePresenter<SmartSearchContract.View>
    {
        void getBingResponse(String search);
    }
}
