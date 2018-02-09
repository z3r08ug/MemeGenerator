package com.example.chris.memegenerator.view.home;

import android.os.Message;
import android.util.Log;

import com.example.chris.memegenerator.data.remote.ImageRemoteDataSource;
import com.example.chris.memegenerator.data.remote.RemoteDataSource;
import com.example.chris.memegenerator.model.pojo.keywordfinder.Keywords;
import com.example.chris.memegenerator.util.Constants;
import com.example.chris.memegenerator.model.pojo.bingsearch.BingSearch;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Admin on 11/29/2017.
 */

public class MemeHomePresenter implements MemeHomeContract.Presenter
{
    RemoteDataSource remoteDataSource;
    ImageRemoteDataSource imageRemoteDataSource;
    MemeHomeContract.View view;
    public static final String TAG = MemeHomePresenter.class.getSimpleName() + "_TAG";
    BingSearch bing = null;
    Keywords keywordResponse;
    List<String> keywords = new ArrayList<>();
    List<String> searchResultsTrending = new ArrayList<>();
    List<String> searchResultsInterests = new ArrayList<>();
    
    @Inject
    public MemeHomePresenter(RemoteDataSource remoteDataSource, ImageRemoteDataSource imageRemoteDataSource)
    {
        this.remoteDataSource = remoteDataSource;
        this.imageRemoteDataSource = imageRemoteDataSource;
    }
    
    public MemeHomePresenter()
    {
    }
    
    @Override
    public void attachView(MemeHomeContract.View view)
    {
        this.view = view;
    }
    
    @Override
    public void detachView()
    {
        this.view = null;
    }
    
    
    @Override
    public void getKeywords(String status)
    {
        Constants.setallFALSE();
        Constants.whichCall(Constants.keyword);
        RemoteDataSource.KeyWordResponse(status)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Keywords>()
                {
    
                    @Override
                    public void onSubscribe(Disposable d)
                    {
                        view.showProgress("Parsing status.....");
                    }
    
                    @Override
                    public void onNext(Keywords keywords)
                    {
                        keywordResponse = keywords;
                    }
    
                    @Override
                    public void onError(Throwable e)
                    {
                        view.showError(e.toString());
                    }
    
                    @Override
                    public void onComplete()
                    {
                        Log.d(TAG, "onComplete: "+keywordResponse.toString());
                        view.setKeywords(keywordResponse);
                    }
                });
    }
    
    @Override
    public void getBingResponseTrending(final String search)
    {
        Log.d(TAG, "getBingResponseTrending: "+search);
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
                            searchResultsTrending.add(bingSearch.getValue().get(i).getThumbnailUrl());
                            Log.d(TAG, "onNext: "+searchResultsTrending.get(i));
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
                        view.setBingResponseTrending(searchResultsTrending);
                    }
                });
    }
    
    @Override
    public void getBingResponseInterests(String search)
    {
        Log.d(TAG, "getBingResponseInterests: "+search);
        ImageRemoteDataSource.getBingInterestsResponse(search)
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
                            searchResultsInterests.add(bingSearch.getValue().get(i).getThumbnailUrl());
                            Log.d(TAG, "onNext: "+searchResultsInterests.get(i));
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
                        view.setBingResponseInterests(searchResultsInterests);
                    }
                });
    }
}
