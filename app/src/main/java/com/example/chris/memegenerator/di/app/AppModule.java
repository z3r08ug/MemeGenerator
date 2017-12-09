package com.example.chris.memegenerator.di.app;


import dagger.Module;

/**
 * Created by chris on 12/7/2017.
 */
@Module
public class AppModule
{
    String BaseURL;
    String apiKey;
    
    public AppModule(String baseURL, String apiKey)
    {
        this.BaseURL = baseURL;
        this.apiKey = apiKey;
    }
    
//    @Provides
//    RemoteDataSource providesRemoteDataSource()
//    {
//        return new RemoteDataSource(BaseURL, apiKey);
//    }
}
