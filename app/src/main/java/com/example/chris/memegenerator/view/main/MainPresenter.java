package com.example.chris.memegenerator.view.main;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.chris.memegenerator.R;
import com.example.chris.memegenerator.data.remote.RemoteDataSource;
import com.example.chris.memegenerator.util.FacebookHandler;
import com.facebook.CallbackManager;
import com.facebook.login.widget.LoginButton;

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
    public void getTopTrending()
    {
    
    }
    
    @Override
    public void getInterestTrending()
    {
    
    }

    @Override
    public void initializeFacebookLogin(LoginButton fbLoginButton) {
        FacebookHandler.getInstance().registerLoginButton(fbLoginButton);
    }
}
