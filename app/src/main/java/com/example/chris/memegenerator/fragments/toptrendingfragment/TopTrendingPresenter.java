package com.example.chris.memegenerator.fragments.toptrendingfragment;

import android.util.Log;

import com.example.chris.memegenerator.data.remote.RemoteDataSource;
import com.example.chris.memegenerator.util.pojo.bingsearch.BingSearch;
import com.example.chris.memegenerator.view.main.MainContract;
import com.example.chris.memegenerator.view.main.MainPresenter;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by  Admin on 1/7/2018.
 */

public class TopTrendingPresenter implements MainContract.Presenter {
    RemoteDataSource remoteDataSource;
    MainContract.View view;
    public static final String TAG = MainPresenter.class.getSimpleName() + "_TAG";
    BingSearch bing = null;
    List<String> memes = new ArrayList<>();
    @Override
    public void attachView(MainContract.View view) {

    }

    @Override
    public void detachView() {

    }

    @Override
    public void getBingSearch(String search) {
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
                        view.showProgress("Downloaded Memes");
                        for (int i = 0; i < bing.getValue().size(); i ++)
                        {
                            if (bing.getValue().get(i) != null)
                            {
                                memes.add(bing.getValue().get(i).getThumbnailUrl());
                            }
                        }
                        view.setBingSearch(memes);

                    }
                });
    }

    @Override
    public void getInterestTrending() {

    }
}
