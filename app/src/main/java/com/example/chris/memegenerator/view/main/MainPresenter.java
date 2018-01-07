package com.example.chris.memegenerator.view.main;

import android.app.ListActivity;
import android.util.Log;

import com.example.chris.memegenerator.data.remote.RemoteDataSource;
import com.example.chris.memegenerator.util.FacebookHandler;
import com.example.chris.memegenerator.util.pojo.bingsearch.BingSearch;
import com.facebook.login.widget.LoginButton;

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
    List<String> memes = new ArrayList<>();
    
    //    private TopTrendingResponse topTrendingResponse;
//    private InterestTrendingResponse interestTrendingResponse;
    @Inject
    public MainPresenter(RemoteDataSource remoteDataSource)
    {
        this.remoteDataSource = remoteDataSource;
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
    public void getTopTrending(final String search)
    {
        RemoteDataSource.getBingResponse(search)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<BingSearch>()
                {
                    @Override
                    public void onSubscribe(Disposable d)
                    {
                        view.showProgress("Downloading memes.....");
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
                        view.setTopTrending(bing);
                        view.showProgress("Downloaded Memes");
                        for (int i = 0; i < bing.getValue().size(); i ++)
                        {
                            if (bing.getValue().get(i) != null)
                            {
                                memes.add(bing.getValue().get(i).getThumbnailUrl());
                            }
                        }
                    }
                });
    }
    
    @Override
    public void getInterestTrending()
    {
    
    }
    
    @Override
    public void initializeFacebookLogin(LoginButton fbLoginButton)
    {
        FacebookHandler.getInstance().registerLoginButton(fbLoginButton);
    }
}
