package com.example.chris.memegenerator.view.createMeme;

import com.example.chris.memegenerator.data.remote.ImageRemoteDataSource;
import com.example.chris.memegenerator.data.remote.RemoteDataSource;
import com.example.chris.memegenerator.util.Constants;
import com.example.chris.memegenerator.model.pojo.bingsearch.BingSearch;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by chris on 12/11/2017.
 */

public class CreateMemePresenter implements CreateMemeContract.Presenter
{
    ImageRemoteDataSource remoteDataSource;
    CreateMemeContract.View view;
    List<String> memes;
    
    @Inject
    public CreateMemePresenter(ImageRemoteDataSource remoteDataSource)
    {
        this.remoteDataSource = remoteDataSource;
        memes = new ArrayList<>();
    }
    
    @Override
    public void attachView(CreateMemeContract.View view)
    {
        this.view = view;
    }
    
    @Override
    public void detachView()
    {
        this.view = null;
    }
    
    @Override
    public void getBingSearch(String search, String whichCall)
    {
        Constants.setallFALSE();
        Constants.whichCall(Constants.bing);
        ImageRemoteDataSource.getBingPicResponse(search)
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
                        for (int i = 0; i < bingSearch.getValue().size(); i ++)
                        {
                            if (bingSearch.getValue().get(i) != null)
                            {
                                memes.add(bingSearch.getValue().get(i).getThumbnailUrl());
                            }
                        }
                    }
                    
                    @Override
                    public void onError(Throwable e)
                    {
                    
                    }
                    
                    @Override
                    public void onComplete()
                    {
                        view.showProgress("Downloaded Memes");
                        view.setBingSearch(memes);
                    }
                });
    }
}
