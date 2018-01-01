package com.example.chris.memegenerator.view.main;

import com.example.chris.memegenerator.data.remote.RemoteDataSource;
import com.example.chris.memegenerator.util.FacebookHandler;
import com.facebook.login.widget.LoginButton;

import javax.inject.Inject;

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
