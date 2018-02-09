package com.example.chris.memegenerator.di.memehome;


import com.example.chris.memegenerator.data.remote.ImageRemoteDataSource;
import com.example.chris.memegenerator.data.remote.RemoteDataSource;
import com.example.chris.memegenerator.view.createMeme.CreateMemePresenter;
import com.example.chris.memegenerator.view.home.MemeHomePresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Admin on 11/29/2017.
 */

@Module
public class MemeHomeModule
{
    @Provides
    MemeHomePresenter providerMemeHomePresenter(RemoteDataSource remoteDataSource, ImageRemoteDataSource imageRemoteDataSource)
    {
        return new MemeHomePresenter(remoteDataSource, imageRemoteDataSource);
    }
}
