package com.example.chris.memegenerator.di.createMeme;


import com.example.chris.memegenerator.data.remote.ImageRemoteDataSource;
import com.example.chris.memegenerator.data.remote.RemoteDataSource;
import com.example.chris.memegenerator.view.createMeme.CreateMemePresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Admin on 11/29/2017.
 */

@Module
public class CreateMemeModule
{
    @Provides
    CreateMemePresenter providerCreateMemePresenter(ImageRemoteDataSource remoteDataSource)
    {
        return new CreateMemePresenter(remoteDataSource);
    }
}
