package com.example.chris.memegenerator.di.main;


import com.example.chris.memegenerator.data.remote.RemoteDataSource;
import com.example.chris.memegenerator.view.main.MainPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Admin on 11/29/2017.
 */

@Module
public class MainModule
{
    @Provides
    MainPresenter providerMainPresenter(RemoteDataSource remoteDataSource)
    {
        return new MainPresenter(remoteDataSource);
    }
}
