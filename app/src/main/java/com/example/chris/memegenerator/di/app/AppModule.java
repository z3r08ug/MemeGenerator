package com.example.chris.memegenerator.di.app;


import com.example.chris.memegenerator.data.remote.RemoteDataSource;

import dagger.Module;
import dagger.Provides;

/**
 * Created by chris on 12/7/2017.
 */
@Module
public class AppModule
{
    String GoogleBaseUrl;
    String apiKey;
    String KeyWordBaseUrl;
    
    public AppModule(String googleBaseUrl, String apiKey, String keyWordBaseUrl)
    {
        this.GoogleBaseUrl = googleBaseUrl;
        this.apiKey = apiKey;
        this.KeyWordBaseUrl = keyWordBaseUrl;
    }
    
    @Provides
    RemoteDataSource providesRemoteDataSource()

    {
        return new RemoteDataSource(GoogleBaseUrl, apiKey,KeyWordBaseUrl);
    }
}
