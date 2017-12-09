package com.example.chris.memegenerator.view.main;

import android.util.Log;
import java.util.List;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Admin on 11/29/2017.
 */

public class MainPresenter implements MainContract.Presenter
{
    MainContract.View view;
    public static final String TAG = MainPresenter.class.getSimpleName() + "_TAG";
//    private TopTrendingResponse topTrendingResponse;
//    private InterestTrendingResponse interestTrendingResponse;
    
    
    @Override
    public void attachView(MainContract.View view)
    {
        this.view = view;
    }

    @Override
    public void detachView()
    {
        this.view = null;
    }
    
    
    @Override
    public void getTopTrending()
    {
    
    }
    
    @Override
    public void getInterestTrending()
    {
    
    }
}
