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
    private static final String BASE_URL = "http://";
    private static final String API_KEY = "AIzaSyBgFi0vAWqYPVS7VvKxV5ZzPiDYcunr7Fo";
    private AppComponent appComponent;
    private MainComponent mainComponent;
    
    @Override
    public void onCreate()
    {
        super.onCreate();
    
        Timber.plant(new Timber.DebugTree());
        
        AppModule appModule = new AppModule(BASE_URL, API_KEY);
        
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
