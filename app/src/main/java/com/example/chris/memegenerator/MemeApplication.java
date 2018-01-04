package com.example.chris.memegenerator;

import android.app.Application;
import android.content.Context;

import com.example.chris.memegenerator.di.app.AppComponent;
import com.example.chris.memegenerator.di.app.AppModule;
import com.example.chris.memegenerator.di.app.DaggerAppComponent;
import com.example.chris.memegenerator.di.main.MainComponent;
import com.example.chris.memegenerator.di.main.MainModule;

import timber.log.Timber;

/**
 * Created by chris on 12/9/2017.
 */

public class MemeApplication extends Application
{
    //private static final String GoogleSerachBaseUrl = "http://"; // TODO not used
    //private static final String API_KEY = "AIzaSyBgFi0vAWqYPVS7VvKxV5ZzPiDYcunr7Fo"; // TODO not used

    private static final String GoogleSerachBaseUrl = "https://www.googleapis.com/customsearch/";
    private static final String API_KEY = "AIzaSyBgFi0vAWqYPVS7VvKxV5ZzPiDYcunr7Fo";
    private static final String KeyWordBaseUrl = "https://api.textgain.com/1/";
    private static final String BingSearchBaseUrl = "https://api.cognitive.microsoft.com/";
    private AppComponent appComponent;
    private MainComponent mainComponent;
    
    @Override
    public void onCreate()
    {
        super.onCreate();
    
        Timber.plant(new Timber.DebugTree());
        
        AppModule appModule = new AppModule(GoogleSerachBaseUrl, API_KEY,KeyWordBaseUrl,BingSearchBaseUrl);
        
        appComponent = DaggerAppComponent.builder()
                .appModule(appModule)
                .build();
    }
    
    public static MemeApplication get(Context context)
    {
        return (MemeApplication) context.getApplicationContext();
    }
    
    public MainComponent getMainComponent()
    {
        mainComponent = appComponent.add(new MainModule());
        return mainComponent;
    }
    public void clearMainComponent()
    {
        mainComponent = null;
    }
}
