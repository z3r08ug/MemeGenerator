package com.example.chris.memegenerator.di.smartsearch;


import com.example.chris.memegenerator.data.remote.ImageRemoteDataSource;
import com.example.chris.memegenerator.view.smartsearch.SmartSearchPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Admin on 11/29/2017.
 */

@Module
public class SmartSearchModule
{
    @Provides
    SmartSearchPresenter providerSmartSearchPresenter(ImageRemoteDataSource imageRemoteDataSource)
    {
        return new SmartSearchPresenter(imageRemoteDataSource);
    }
}
