package com.example.chris.memegenerator.view.main;

import android.util.Log;

import com.example.chris.memegenerator.data.remote.RemoteDataSource;
import com.example.chris.memegenerator.util.Constants;
import com.example.chris.memegenerator.util.pojo.bingsearch.BingSearch;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Admin on 11/29/2017.
 */

public class MainPresenter implements MainContract.Presenter
{
    RemoteDataSource remoteDataSource;
    MainContract.View view;
    public static final String TAG = MainPresenter.class.getSimpleName() + "_TAG";
    BingSearch bing = null;
    List<String> trendingMemes = new ArrayList<>();
    List<String> interestMemes = new ArrayList<>();
    
    @Inject
    public MainPresenter(RemoteDataSource remoteDataSource)
    {
        this.remoteDataSource = remoteDataSource;
    }

    public MainPresenter()
    {
    }

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
    public void getBingSearch(final String search, final String whichcall)
    {
        Constants.whichCall(Constants.bing);
        RemoteDataSource.getBingResponse(search)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<BingSearch>()
                {
                    @Override
                    public void onSubscribe(Disposable d)
                    {
                        view.showProgress("Downloading trendingMemes.....");
                    }
                    
                    @Override
                    public void onNext(BingSearch bingSearch)
                    {
                        bing = bingSearch;
                    }
                    
                    @Override
                    public void onError(Throwable e)
                    {

                    }
                    
                    @Override
                    public void onComplete()
                    {
                        view.showProgress("Downloaded Memes");
                        for (int i = 0; i < bing.getValue().size(); i ++)
                        {
                            if (bing.getValue().get(i) != null)
                            {
                                if (whichcall == Constants.topTrending)
                                    trendingMemes.add(bing.getValue().get(i).getThumbnailUrl());
                                else
                                    interestMemes.add(bing.getValue().get(i).getThumbnailUrl());
                            }
                        }
                        if(whichcall == Constants.topTrending)
                        {
                            view.setBingSearch(trendingMemes);
                        }
                        else if(whichcall==Constants.interestTrending) {
                            view.setInterestBingSearch(interestMemes);
                        }
                        else if(whichcall == Constants.Searchmeme){
                            view.setSearchmeme(interestMemes);
                        }
                    }
                });
    }
//    @Override
//    public void getInterestTrending()
//    {
//
//    }
}
