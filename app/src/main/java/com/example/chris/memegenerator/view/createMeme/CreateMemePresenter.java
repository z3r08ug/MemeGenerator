package com.example.chris.memegenerator.view.createMeme;

import com.example.chris.memegenerator.data.remote.RemoteDataSource;
import com.example.chris.memegenerator.util.BaseView;
import com.example.chris.memegenerator.view.main.MainContract;
import com.example.chris.memegenerator.view.main.MainPresenter;

import javax.inject.Inject;

/**
 * Created by chris on 12/11/2017.
 */

public class CreateMemePresenter implements CreateMemeContract.Presenter
{
    RemoteDataSource remoteDataSource;
    CreateMemeContract.View view;
   
    @Inject
    public CreateMemePresenter(RemoteDataSource remoteDataSource)
    {
        this.remoteDataSource = remoteDataSource;
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
}
