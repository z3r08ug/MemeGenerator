package com.example.chris.memegenerator.view.smartsearch;

import android.util.Log;

import com.example.chris.memegenerator.data.remote.ImageRemoteDataSource;
import com.example.chris.memegenerator.data.remote.RemoteDataSource;
import com.example.chris.memegenerator.model.pojo.bingsearch.BingSearch;
import com.example.chris.memegenerator.view.home.MemeHomeContract;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by chris on 2/8/2018.
 */

public class SmartSearchPresenter implements SmartSearchContract.Presenter
{
    ImageRemoteDataSource imageRemoteDataSource;
    SmartSearchContract.View view;
    private List<String> memes = new ArrayList<>();
    public static final String TAG = SmartSearchPresenter.class.getSimpleName() + "_TAG";
    
    @Inject
    public SmartSearchPresenter(ImageRemoteDataSource imageRemoteDataSource)
    {
        this.imageRemoteDataSource = imageRemoteDataSource;
    }
    
    public SmartSearchPresenter()
    {
    
    }
    
    @Override
    public void attachView(SmartSearchContract.View view)
    {
        this.view = view;
    }
    
    @Override
    public void detachView()
    {
        this.view = null;
    }
    
    @Override
    public void getBingResponse(String search)
    {
        ImageRemoteDataSource.getBingTrendingResponse(search)
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
                        for(int i = 0; i < bingSearch.getValue().size(); i++)
                        {
                            memes.add(bingSearch.getValue().get(i).getThumbnailUrl());
                            Log.d(TAG, "onNext: "+memes.get(i));
                        }
                    }
                
                    @Override
                    public void onError(Throwable e)
                    {
                        view.showError(e.toString());
                    }
                
                    @Override
                    public void onComplete()
                    {
                        view.setBingResponse(memes);
                    }
                });
    }
}
